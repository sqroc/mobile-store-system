package ms.service;

import java.util.List;

import ms.model.Cart;
import ms.model.OrderItem;


public interface OrderItemManager {

	public abstract String add(OrderItem orderItem) throws Exception;

	public abstract int deleletByid(int oid) throws Exception;

	public abstract String modify(OrderItem orderItem) throws Exception;

	public List<OrderItem> getOrderItems(int start ,int limit,int cid) throws Exception;

	public OrderItem loadById(int oid) throws Exception;
	
	public int getTotalNum(int cid) throws Exception;
	
	public int deleletByCard_id(int id);
	
	public List<OrderItem> getOrderItembyCart(Cart cart);
}
