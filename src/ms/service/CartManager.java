package ms.service;

import java.util.List;

import ms.model.Cart;


public interface CartManager {

	public abstract String add(Cart cart) throws Exception;

	public abstract int deleletByid(int cart_id) throws Exception;

	public abstract String modify(Cart cart) throws Exception;

	public List<Cart> getCarts(String[] paramter,int start ,int limit,int type) throws Exception;
	
	public List<Cart> getCartsByUid(int uid,int start ,int limit) throws Exception;

	public Cart loadById(int cart_id) throws Exception;
	
	public int getTotalNum(String[] paramter,int type) throws Exception;
	
	public Cart loadByNum(String num);
}
