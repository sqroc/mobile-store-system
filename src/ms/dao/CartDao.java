package ms.dao;

import java.util.Date;
import java.util.List;

import ms.model.Cart;


public interface CartDao {

	public void save(Cart cart);

	public int deleletByid(int id);

	public void modify(Cart cart);
	
	public List<Cart> getCarts(int start, int limit);
	
	public List<Cart> getCartsByNum(String num,int start, int limit);
	
	public List<Cart> getCartsByUid(int uid,int start, int limit);
	
	public List<Cart> getCartsByState(String state,int start, int limit);
	
	public List<Cart> getCartsByDate(Date date1,Date date2,int start, int limit);
	
	public List<Cart> getCartsByDateAndState(Date date1,Date date2,String state,int start, int limit);
	
	public int getTotalNum();
	
	public int getTotalNumByNum(String num);
	
	public int getTotalNumByState(String state);
	
	public int getTotalNumByDate(Date date1,Date date2);
	
	public int getTotalNumByDateAndState(Date date1,Date date2,String state);
	
	public Cart loadByNum(String num);
}
