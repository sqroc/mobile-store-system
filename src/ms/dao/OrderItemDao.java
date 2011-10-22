package ms.dao;

import java.util.List;

import ms.model.Cart;
import ms.model.OrderItem;


public interface OrderItemDao {

	public void save(OrderItem orderItem);

	public int deleletByid(int id);
	
	public int deleletByCard_id(int id);

	public void modify(OrderItem orderItem);
	
	public List<OrderItem> getOrderItems(int start, int limit,int cid);
	
	public int getTotalNum(int cid);
	
	public List<OrderItem> getOrderItembyCart(Cart cart);
}
