package ms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ms.dao.OrderItemDao;
import ms.model.Cart;
import ms.model.Cartinfo;
import ms.model.Category;
import ms.model.OrderItem;

@Repository("OrderItemDao")
public class OrderItemDaoImpl implements OrderItemDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public void save(OrderItem orderItem) {
		hibernateTemplate.save(orderItem);

	}

	public int deleletByid(int id) {
		OrderItem orderItemtemp = new OrderItem();
		orderItemtemp.setOid(id);
		try {
			hibernateTemplate.delete(orderItemtemp);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}

	public void modify(OrderItem orderItem) {
		hibernateTemplate.update(orderItem);

	}

	public List<OrderItem> getOrderItems(final int start, final int limit,final int cid) {
		List<OrderItem> orderItems = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from OrderItem where cart_id="+cid);

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<OrderItem> list = (List<OrderItem>) query.list();
						return list;
					}
				});

		if ( orderItems != null) {
			for (int i = 0; i <  orderItems.size(); i++) {
				int id  = ( orderItems.get(i).getItem().getItem_id());
				orderItems.get(i).setItemId(id);

			}

		}

	

		
		return orderItems;
	}
	
	public int getTotalNum(int cid) {
		String hql = "select count(*) from OrderItem  where cart_id="+cid;
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public int deleletByCard_id(int id) {
		String queryString = "from  OrderItem orderItem where cart_id=" + id;
		List<OrderItem> orderItems = hibernateTemplate.find(queryString);
		for (OrderItem orderItem : orderItems) {
		hibernateTemplate.delete(orderItem);
		}
		return 1;
	}

	public List<OrderItem> getOrderItembyCart(Cart cart) {
		String queryString = "from  OrderItem orderItem where orderItem.cart.cart_id=" + cart.getCart_id();
		List<OrderItem> orderItems = hibernateTemplate.find(queryString);
		return orderItems;
	}

}
