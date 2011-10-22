package ms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ms.dao.StorageDao;
import ms.model.Item;
import ms.model.Storage;

@Repository("StorageDao")
public class StorageDaoImpl implements StorageDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	
	public void save(Storage storage) {
		hibernateTemplate.save(storage);

	}

	public int deleletByid(int id) {
		Storage storaget = new Storage();
		storaget.setSid(id);
		try {
			hibernateTemplate.delete(storaget);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}

	public void modify(Storage storage) {
		hibernateTemplate.update(storage);

	}

	public List<Storage> getStorages(final int start, final int limit) {
		List<Storage> storages = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Storage");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Storage> list = (List<Storage>) query.list();
						return list;
					}
				});

		return storages;
	}

	public int getTotalNum() {
		String hql = "select count(*) from Storage as storage";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

}
