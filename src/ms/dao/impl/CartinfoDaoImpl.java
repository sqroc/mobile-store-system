package ms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ms.dao.CartinfoDao;
import ms.model.Cartinfo;
import ms.model.Item;
import ms.model.OrderItem;
@Repository("CartinfoDao")
public class CartinfoDaoImpl implements CartinfoDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public void save(Cartinfo cartinfo) {
		hibernateTemplate.save(cartinfo);

	}

	public int deleletByid(int id) {
		Cartinfo cartinfotemp = new Cartinfo();
		cartinfotemp.setCiid(id);
		try {
			hibernateTemplate.delete(cartinfotemp);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}

	public void modify(Cartinfo cartinfo) {
		hibernateTemplate.update(cartinfo);

	}

	public List<Cartinfo> getCartinfos(final int start, final int limit, final int cid) {
		List<Cartinfo> cartinfos = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Cartinfo where uid="+cid);

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Cartinfo> list = (List<Cartinfo>) query.list();
						return list;
					}
				});

		return cartinfos;
	}

	public List<Cartinfo> loadbyuid(int uid) {
		String queryString = "from  Cartinfo cartinfo where cartinfo.uid=" + uid +"and cartinfo.isorder=0";

		List<Cartinfo> cartinfos = hibernateTemplate.find(queryString);

		return cartinfos;
	}

	public int deleletByUid(int id) {
		String queryString = "from  Cartinfo cartinfo where cartinfo.uid=" + id +"and cartinfo.isorder=1";
		List<Cartinfo> cartinfos = hibernateTemplate.find(queryString);
		for (Cartinfo cartinfo : cartinfos) {
		hibernateTemplate.delete(cartinfo);
		}
		return 1;
	}

}
