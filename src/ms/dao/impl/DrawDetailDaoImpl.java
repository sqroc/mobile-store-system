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

import ms.dao.DrawDetailDao;
import ms.model.DrawDetail;
import ms.model.DrawItem;

@Repository("DrawDetailDao")
public class DrawDetailDaoImpl implements DrawDetailDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(DrawDetail drawDetail) {
		hibernateTemplate.save(drawDetail);

	}

	public int deleletByid(int id) {
		DrawDetail drawDetail = new DrawDetail();
		drawDetail.setItemno(id);
		try {
			hibernateTemplate.delete(drawDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public void modify(DrawDetail drawDetail) {
		hibernateTemplate.update(drawDetail);

	}

	public List<DrawDetail> getDrawDetails(final int start, final int limit) {
		List<DrawDetail> drawDetails = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from DrawDetail");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<DrawDetail> list = (List<DrawDetail>) query.list();
						return list;
					}
				});

		return drawDetails;
	}
	public List<DrawDetail> getDrawDetailsByNum(String num)
	{
		
		String queryString = "from  DrawDetail drawDetail where drawDetail.code='" + num
				+ "'";

		List<DrawDetail> drawDetails = hibernateTemplate.find(queryString);

		return drawDetails;
		
	}
	public List<DrawDetail> getDrawDetailsByNum(final String num,
			final int start, final int limit) {
		List<DrawDetail> drawDetails = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from DrawDetail as drawDetail where drawDetail.code='"
										+ num + "'");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<DrawDetail> list = (List<DrawDetail>) query.list();
						return list;
					}
				});

		return drawDetails;
	}

	public List<DrawDetail> getDrawDetailsByDate(final Date date1, final Date date2,
			final int start, final int limit) {
		List<DrawDetail>drawDetails = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from DrawDetail as drawDetail where drawDetail.billDate<=:date2 and drawDetail.billDate>=:date1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);
						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<DrawDetail> list = (List<DrawDetail>) query.list();
						return list;
					}
				});

		return drawDetails;
	}

	public int getTotalNum() {
		String hql = "select count(*) from DrawDetail as drawDetail ";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public int getTotalNumByNum(String num) {
		String hql = "select count(*) from DrawDetail as drawDetail where drawDetail.code='"
				+ num + "'";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public int getTotalNumByDate(final Date date1, final Date date2) {
		List<Long> nums = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("select count(*) from DrawDetail drawDetail where drawDetail.billDate<=:date2 and drawDetail.billDate>=:date1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);
						

						List<Long> nums = (List<Long>) query.list();
						return nums;
					}
				});
		
		return nums.get(0).intValue();
	}

	public DrawDetail loadByNum(String num) {
		String queryString = "from  DrawDetail drawDetail where drawDetail.code='" + num
				+ "'";

		List<DrawDetail> drawDetails = hibernateTemplate.find(queryString);

		return drawDetails.get(0);
	}

}
