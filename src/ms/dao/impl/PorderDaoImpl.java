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

import ms.dao.PorderDao;
import ms.model.Cart;
import ms.model.Porder;

@Repository("PorderDao")
public class PorderDaoImpl implements PorderDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(Porder porder) {
		hibernateTemplate.save(porder);

	}

	public int deleletByid(int id) {
		Porder porder = new Porder();
		porder.setPorid(id);
		try {
			hibernateTemplate.delete(porder);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public void modify(Porder porder) {
		hibernateTemplate.update(porder);

	}

	public List<Porder> getPorders(final int start, final int limit) {
		List<Porder> porders = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Porder");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Porder> list = (List<Porder>) query.list();
						return list;
					}
				});

		return porders;
	}

	public List<Porder> getPordersByNum(final String num, final int start,
			final int limit) {
		List<Porder> porders = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Porder as porder where porder.code='"
										+ num + "'");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Porder> list = (List<Porder>) query.list();
						return list;
					}
				});

		return porders;
	}

	public List<Porder> getPordersByDate(final Date date1, final Date date2,
			final int start, final int limit) {
		List<Porder> porders = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Porder as porder where porder.billDate<=:date2 and porder.billDate>=:date1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);
						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Porder> list = (List<Porder>) query.list();
						return list;
					}
				});

		return porders;
	}

	public int getTotalNum() {
		String hql = "select count(*) from Porder as porder ";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public int getTotalNumByNum(String num) {
		String hql = "select count(*) from Porder as porder where porder.code='"
				+ num + "'";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public int getTotalNumByDate(final Date date1, final Date date2) {
		List<Long> nums = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("select count(*) from Porder porder where porder.billDate<=:date2 and porder.billDate>=:date1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);
						

						List<Long> nums = (List<Long>) query.list();
						return nums;
					}
				});
		
		return nums.get(0).intValue();
	}

	public Porder loadByNum(String num) {
		
		System.out.println(num);
		System.out.println(num);
		
		String queryString = "from  Porder porder where porder.code='" + num
				+ "'";

		if(hibernateTemplate==null)
		{
			System.out.println("hibernateTemplate"+"为空");
		}
		List<Porder> porders = hibernateTemplate.find(queryString);

		return porders.get(0);
	}

}
