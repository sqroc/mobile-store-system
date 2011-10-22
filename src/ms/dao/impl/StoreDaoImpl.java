package ms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ms.dao.StoreDao;
import ms.model.Category;
import ms.model.Store;

@Repository("StoreDao")
public class StoreDaoImpl implements StoreDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(Store store) {
		hibernateTemplate.save(store);

	}

	public int deleletByid(int id) {
		Store store = new Store();
		store.setStoreid(id);
		try {
			hibernateTemplate.delete(store);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		return 1;
	}

	public void modify(Store store) {
		hibernateTemplate.update(store);

	}

	public List<Store> getStores(final int start, final int limit) {
		List<Store> stores = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Store");
						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Store> list = (List<Store>) query.list();
						return list;
					}
				});

		return stores;
	}

	public List<Store> getAllStores() {
		String queryString = "from  Store ";

		List<Store> stores = hibernateTemplate.find(queryString);

		return stores;
	}

	public List<String> getNames() {
		String queryString = "select name from Store as store";
		List<String> CNames = hibernateTemplate.find(queryString);

		return CNames;
	}

	public int getTotalNum() {
		String hql = "select count(*) from Store as store";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public Store loadByName(String name) throws Exception {
		String queryString = "from Store store where store.name='" + name + "'";

		List<Store> stores = hibernateTemplate.find(queryString);

		return stores.get(0);
	}

	public List<Store> loadByNames(String name) throws Exception {
		String queryString = "from  Store store where store.name='" + name
				+ "'";

		List<Store> stores = hibernateTemplate.find(queryString);

		return stores;
	}

}
