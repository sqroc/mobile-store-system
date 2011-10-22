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

import ms.dao.DrawItemDao;
import ms.model.DrawItem;
import ms.model.Porder;

@Repository("DrawItemDao")
public class DrawItemDaoImpl implements DrawItemDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(DrawItem drawItem) {
		hibernateTemplate.save(drawItem);

	}

	public int deleletByid(int id) {
		DrawItem drawItem = new DrawItem();
		drawItem.setDrawItemId(id);
		try {
			hibernateTemplate.delete(drawItem);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public void modify(DrawItem drawItem) {
		hibernateTemplate.update(drawItem);

	}

	public List<DrawItem> getDrawItems(final int start, final int limit) {
		List<DrawItem>drawItems = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from DrawItem");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<DrawItem> list = (List<DrawItem>) query.list();
						return list;
					}
				});

		return drawItems;
	}

	public List<DrawItem> getDrawItemsByNum(final String num, final int start, final int limit) {
		List<DrawItem>drawItems = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from DrawItem as drawItem where drawItem.code='"
										+ num + "'");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<DrawItem> list = (List<DrawItem>) query.list();
						return list;
					}
				});

		return drawItems;
	}

	public List<DrawItem> getDrawItemsByDate(final Date date1, final Date date2, final int start,
			final int limit) {
		List<DrawItem>drawItems = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from DrawItem as drawItem where drawItem.billDate<=:date2 and drawItem.billDate>=:date1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);
						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<DrawItem> list = (List<DrawItem>) query.list();
						return list;
					}
				});

		return drawItems;
	}

	public int getTotalNum() {
		String hql = "select count(*) from DrawItem as drawItem ";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public int getTotalNumByNum(String num) {
		String hql = "select count(*) from DrawItem as drawItem where drawItem.code='"
				+ num + "'";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public int getTotalNumByDate(final Date date1, final Date date2) {
		List<Long> nums = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("select count(*) from DrawItem drawItem where drawItem.billDate<=:date2 and drawItem.billDate>=:date1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);
						

						List<Long> nums = (List<Long>) query.list();
						return nums;
					}
				});
		
		return nums.get(0).intValue();
	}

	public DrawItem loadByNum(String num) {
		String queryString = "from  DrawItem drawItem where drawItem.code='" + num
				+ "'";

		List<DrawItem> drawItems = hibernateTemplate.find(queryString);

		return drawItems.get(0);
	}

}
