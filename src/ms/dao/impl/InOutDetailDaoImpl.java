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

import ms.dao.InOutDetailDao;
import ms.model.BackDraw;
import ms.model.InOutDetail;

@Repository("InOutDetailDao")
public class InOutDetailDaoImpl implements InOutDetailDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(InOutDetail inOutDetail) {
		hibernateTemplate.save(inOutDetail);

	}

	public int deleletByid(int id) {
		InOutDetail inOutDetail = new InOutDetail();
		inOutDetail.setIODid(id);
		try {
			hibernateTemplate.delete(inOutDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public void modify(InOutDetail inOutDetail) {
		hibernateTemplate.update(inOutDetail);

	}

	public List<InOutDetail> getInOutDetails(final int start, final int limit) {
		List<InOutDetail>inOutDetails = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from InOutDetail");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<InOutDetail> list = (List<InOutDetail>) query.list();
						return list;
					}
				});

		return inOutDetails;
	}

	public List<InOutDetail> getInOutDetailsByDate(final Date date1,
			final Date date2) {
		List<InOutDetail> inOutDetails = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from InOutDetail inOutDetail where inOutDetail.date<=:date2 and inOutDetail.date>=:date1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);

						List<InOutDetail> list = (List<InOutDetail>) query
								.list();
						return list;
					}
				});
		return inOutDetails;
	}

	public int getTotalNum() {
		String hql = "select count(*) from InOutDetail as inOutDetail ";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public int getInOutDetailsByDateAndCategory(final Date date1, final Date date2, final int cid) {
		List<Long> nums = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("select count(*) from InOutDetail inOutDetail where inOutDetail.date<=:date2 and inOutDetail.date>=:date1 and cid='"
										+ cid + "'and inOutDetail.satid=1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);

						List<Long> nums = (List<Long>) query.list();
						return nums;
					}
				});

		return nums.get(0).intValue();
	}

	public int getInOutDetailsByDateAndItem(final Date date1, final Date date2, final int item_id) {
		List<Long> nums = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("select count(*) from InOutDetail inOutDetail where inOutDetail.date<=:date2 and inOutDetail.date>=:date1 and item_id='"
										+ item_id + "'and inOutDetail.satid=1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);

						List<Long> nums = (List<Long>) query.list();
						return nums;
					}
				});

		return nums.get(0).intValue();
	}

	public List<InOutDetail> getInOutDetailsByDate(final Date date1, final Date date2,
			final int item_id) {
		List<InOutDetail> inOutDetails = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from InOutDetail inOutDetail where inOutDetail.date<=:date2 and inOutDetail.date>=:date1 and item_id="
										+ item_id);
						query.setDate("date2", date2);
						query.setDate("date1", date1);

						List<InOutDetail> list = (List<InOutDetail>) query
								.list();
						return list;
					}
				});
		return inOutDetails;
	}

}
