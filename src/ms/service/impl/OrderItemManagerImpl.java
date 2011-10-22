package ms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import ms.dao.OrderItemDao;
import ms.model.Cart;
import ms.model.OrderItem;
import ms.service.OrderItemManager;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

@Service("orderItemManager")
@RemotingDestination(channels = { "my-amf" })
public class OrderItemManagerImpl implements OrderItemManager {
	private OrderItemDao orderItemDao;
	
	

	public OrderItemDao getOrderItemDao() {
		return orderItemDao;
	}
	@Resource
	public void setOrderItemDao(OrderItemDao orderItemDao) {
		this.orderItemDao = orderItemDao;
	}
	@RemotingInclude
	public String add(OrderItem orderItem) throws Exception {
		orderItemDao.save(orderItem);
		
		return "succeed";
	}
	@RemotingInclude
	public int deleletByid(int oid) throws Exception {
		return orderItemDao.deleletByid(oid);
	}
	@RemotingInclude
	public String modify(OrderItem orderItem) throws Exception {
		orderItemDao.modify(orderItem);
		return "succeed";
	}
	@RemotingInclude
	public List<OrderItem> getOrderItems(int start, int limit,int cid) throws Exception {
		return orderItemDao.getOrderItems(start, limit,cid);
	}
	@RemotingInclude
	public OrderItem loadById(int oid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@RemotingInclude
	public int getTotalNum(int cid) throws Exception {
		return orderItemDao.getTotalNum(cid);
	}
	@RemotingInclude
	public int deleletByCard_id(int id) {
		// TODO Auto-generated method stub
		return orderItemDao.deleletByCard_id(id);
	}
	public List<OrderItem> getOrderItembyCart(Cart cart) {
		// TODO Auto-generated method stub
		return orderItemDao.getOrderItembyCart(cart);
	}

}
