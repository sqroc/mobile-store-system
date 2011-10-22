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

import ms.dao.BackDrawDao;
import ms.model.BackDraw;
import ms.model.DrawItem;
@Repository("BackDrawDao")
public class BackDrawDaoImpl implements BackDrawDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	public void save(BackDraw backDraw) {
		hibernateTemplate.save(backDraw);

	}

	public int deleletByid(int id) {
		BackDraw backDraw = new BackDraw();
		backDraw.setBackDrawId(id);
		try {
			hibernateTemplate.delete(backDraw);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public void  modify(BackDraw backDraw) {
		hibernateTemplate.update(backDraw);
		

	}

	public List<BackDraw> getBackDraws(final int start, final int limit) {
		List<BackDraw>backDraws = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from BackDraw");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<BackDraw> list = (List<BackDraw>) query.list();
						return list;
					}
				});

		return backDraws;
	}

	public List<BackDraw> getBackDrawsByNum(final String num, final int start, final int limit) {
		List<BackDraw>backDraws = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from BackDraw as backDraw where backDraw.code='"
										+ num + "'");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<BackDraw> list = (List<BackDraw>) query.list();
						return list;
					}
				});

		return backDraws;
	}

	public List<BackDraw> getBackDrawsByDate(final Date date1, final Date date2, final int start,
			final int limit) {
		List<BackDraw>backDraws = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from BackDraw as BackDraw where BackDraw.billDate<=:date2 and BackDraw.billDate>=:date1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);
						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<BackDraw> list = (List<BackDraw>) query.list();
						return list;
					}
				});

		return backDraws;
	}

	public int getTotalNum() {
		String hql = "select count(*) from BackDraw as backDraw ";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public int getTotalNumByNum(String num) {
		String hql = "select count(*) from BackDraw as backDraw where backDraw.code='"
				+ num + "'";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public int getTotalNumByDate(final Date date1, final Date date2) {
		List<Long> nums = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("select count(*) from BackDraw backDraw where backDraw.billDate<=:date2 and backDraw.billDate>=:date1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);
						

						List<Long> nums = (List<Long>) query.list();
						return nums;
					}
				});
		
		return nums.get(0).intValue();
	}

	public BackDraw loadByNum(String num) {
		String queryString = "from  BackDraw backDraw where backDraw.code='" + num
				+ "'";

		List<BackDraw> backDraws = hibernateTemplate.find(queryString);

		return backDraws.get(0);
	}

}
