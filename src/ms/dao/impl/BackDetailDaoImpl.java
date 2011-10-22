package ms.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ms.dao.BackDetailDao;
import ms.model.BackDetail;
import ms.model.BackDraw;

@Repository("BackDetailDao")
public class BackDetailDaoImpl implements BackDetailDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(BackDetail backDetail) {
		hibernateTemplate.save(backDetail);

	}

	public int deleletByid(int id) {
		BackDetail backDetail = new BackDetail();
		backDetail.setBackDid(id);
		try {
			hibernateTemplate.delete(backDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public void modify(BackDetail backDetail) {
		hibernateTemplate.update(backDetail);

	}

	public List<BackDetail> getBackDetails(final int start, final int limit) {
		List<BackDetail> backDetails = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from BackDetail");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<BackDetail> list = (List<BackDetail>) query.list();
						return list;
					}
				});

		return backDetails;
	}
	
	
	public List<BackDetail> getBackDetailsByNum(String num) {
		// TODO Auto-generated method stub
		String queryString = "from  BackDetail backDetail where backDetail.code='"
				+ num + "'";

		List<BackDetail> backDetails = hibernateTemplate.find(queryString);

		return backDetails;
	}

	public List<BackDetail> getBackDetailsByNum(final String num,
			final int start, final int limit) {
		List<BackDetail> backDetails = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from BackDetail as backDetail where backDetail.code='"
										+ num + "'");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<BackDetail> list = (List<BackDetail>) query.list();
						return list;
					}
				});

		return backDetails;
	}

	public List<BackDetail> getBackDetailsByDate(final Date date1,
			final Date date2, final int start, final int limit) {
		List<BackDetail> backDetails = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from BackDetail as backDetail where backDetail.billDate<=:date2 and backDetail.billDate>=:date1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);
						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<BackDetail> list = (List<BackDetail>) query.list();
						return list;
					}
				});

		return backDetails;
	}

	public int getTotalNum() {
		String hql = "select count(*) from BackDetail as backDetail ";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public int getTotalNumByNum(String num) {
		String hql = "select count(*) from BackDetail as backDetail where backDetail.code='"
				+ num + "'";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public int getTotalNumByDate(final Date date1, final Date date2) {
		List<Long> nums = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("select count(*) from BackDetail backDetail where backDetail.billDate<=:date2 and backDetail.billDate>=:date1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);

						List<Long> nums = (List<Long>) query.list();
						return nums;
					}
				});

		return nums.get(0).intValue();
	}

	public BackDetail loadByNum(String num) {
		String queryString = "from  BackDetail backDetail where backDetail.code='"
				+ num + "'";

		List<BackDetail> backDetails = hibernateTemplate.find(queryString);

		return backDetails.get(0);
	}

}
