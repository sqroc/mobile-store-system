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

import ms.dao.CartDao;
import ms.model.Cart;
import ms.model.Item;
import ms.model.User;

@Repository("CartDao")
public class CartDaoImpl implements CartDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public void save(Cart cart) {
		hibernateTemplate.save(cart);

	}

	public int deleletByid(int id) {
		Cart carttemp = new Cart();
		carttemp.setCart_id(id);
		try {
			hibernateTemplate.delete(carttemp);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	public void modify(Cart cart) {
		hibernateTemplate.update(cart);

	}

	public List<Cart> getCarts(final int start, final int limit) {
		List<Cart> carts = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Cart");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Cart> list = (List<Cart>) query.list();
						return list;
					}
				});

		
		if (carts != null) {
			for (int i = 0; i < carts.size(); i++) {
				int id = (carts.get(i).getUser().getUid());
				carts.get(i).setUserId(id);

			}

		}

		return carts;
	}

	public int getTotalNum() {
		String hql = "select count(*) from Cart as cart ";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public Cart loadByNum(String num) {
		
		System.out.println(num);
		String queryString = "from  Cart cart where cart.num='" + num
				+ "'";

		List<Cart> carts = hibernateTemplate.find(queryString);

		return carts.get(0);
	}

	public List<Cart> getCartsByNum(final String num, final int start, final int limit) {
		List<Cart> carts = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Cart cart where cart.num='" + num
				+ "'");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Cart> list = (List<Cart>) query.list();
						return list;
					}
				});
		return carts;
	}

	public int getTotalNumByNum(String num) {
		String hql = "select count(*) from Cart as cart where cart.num='" + num
				+ "'";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public List<Cart> getCartsByState(final String state, final int start, final int limit) {
		List<Cart> carts = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Cart cart where cart.state='" + state
				+ "'");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Cart> list = (List<Cart>) query.list();
						return list;
					}
				});
		return carts;
	}

	public int getTotalNumByState(String state) {
		String hql = "select count(*) from Cart as cart where cart.state='" + state
				+ "'";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public List<Cart> getCartsByDate(final Date date1, final Date date2, final int start,
			final int limit) {
		List<Cart> carts = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Cart cart where cart.date<=:date2 and cart.date>=:date1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);
						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Cart> list = (List<Cart>) query.list();
						return list;
					}
				});
		
		return carts;
	}

	public List<Cart> getCartsByDateAndState(final Date date1, final Date date2,
			final String state, final int start, final int limit) {
		List<Cart> carts = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Cart cart where cart.date<=:date2 and cart.date>=:date1 and cart.state='"+ state+"'");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Cart> list = (List<Cart>) query.list();
						return list;
					}
				});
		
		return carts;
	}

	public int getTotalNumByDate(final Date date1, final Date date2) {
		List<Long> nums = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("select count(*) from Cart cart where cart.date<=:date2 and cart.date>=:date1");
						query.setDate("date2", date2);
						query.setDate("date1", date1);
						

						List<Long> nums = (List<Long>) query.list();
						return nums;
					}
				});
		
		return nums.get(0).intValue();
		
		
	}

	public int getTotalNumByDateAndState(final Date date1, final Date date2, final String state) {
		
		List<Long> nums = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("select count(*) from Cart cart where cart.date<=:date2 and cart.date>=:date1 and cart.state='"+state+"'");
						query.setDate("date2", date2);
						query.setDate("date1", date1);
						

						List<Long> nums = (List<Long>) query.list();
						return nums;
					}
				});
	
		return nums.get(0).intValue();
		
	}

	public List<Cart> getCartsByUid(final int uid, final int start, final int limit) {
		List<Cart> carts = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Cart cart where uid='" + uid
				+ "'");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Cart> list = (List<Cart>) query.list();
						return list;
					}
				});
		return carts;
	}

}
