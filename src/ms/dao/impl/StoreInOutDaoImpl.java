package ms.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ms.dao.StoreInOutDao;
import ms.model.Cart;
import ms.model.Category;
import ms.model.InOutDetail;
import ms.model.StoreInOut;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository("StoreInOutDao")
public class StoreInOutDaoImpl implements StoreInOutDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public double getTotalMByDate(final Date date1, final Date date2) {
		double m = 0;
		List<StoreInOut> storeInOuts = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from StoreInOut storeInOut where storeInOut.billDate<=:date2 and storeInOut.billDate>=:date1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);

						List<StoreInOut> list = (List<StoreInOut>) query.list();
						return list;
					}
				});
		for (int i = 0; i < storeInOuts.size(); i++) {
			m += storeInOuts.get(i).getTotalM();
		}
		return m;
	}

	public List<StoreInOut> getStoreInOutByDate(final Date date1,
			final Date date2) {
		List<StoreInOut> storeInOuts = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from StoreInOut storeInOut where storeInOut.billDate<=:date2 and storeInOut.billDate>=:date1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);

						List<StoreInOut> list = (List<StoreInOut>) query.list();
						return list;
					}
				});
		return storeInOuts;
	}

	public void save(StoreInOut storeInOut) {
		hibernateTemplate.save(storeInOut);

	}

	public int deleletByid(int id) {
		StoreInOut storeInOut = new StoreInOut();
		storeInOut.setSatid(id);
		try {
			hibernateTemplate.delete(storeInOut);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		return 1;
	}

	public void modify(StoreInOut storeInOut) {
		hibernateTemplate.update(storeInOut);

	}

	public List<StoreInOut> getStoreInOuts(final int start, final int limit) {
		List<StoreInOut> storeInOuts = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from StoreInOut");
						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<StoreInOut> list = (List<StoreInOut>) query.list();
						return list;
					}
				});

		return storeInOuts;
	}

	public List<StoreInOut> getAllStoreInOuts() {
		String queryString = "from  StoreInOut ";

		List<StoreInOut> storeInOuts = hibernateTemplate.find(queryString);

		return storeInOuts;
	}

	public int getTotalNum() {
		String hql = "select count(*) from StoreInOut as storeInOut";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public int getTotalNumByDate(final Date date1, final Date date2) {
		List<Long> nums = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("select count(*) from StoreInOut storeInOut where storeInOut.billDate<=:date2 and storeInOut.billDate>=:date1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);

						List<Long> nums = (List<Long>) query.list();
						return nums;
					}
				});

		return nums.get(0).intValue();
	}

	public List<StoreInOut> getStoreInOutByDate(final Date date1, final Date date2,
			final int start, final int limit) {
		List<StoreInOut> storeInOuts = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from StoreInOut storeInOut where storeInOut.billDate<=:date2 and storeInOut.billDate>=:date1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);
						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<StoreInOut> list = (List<StoreInOut>) query.list();
						return list;
					}
				});
		
		return storeInOuts;
	}

	

}
