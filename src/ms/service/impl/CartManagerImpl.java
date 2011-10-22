package ms.service.impl;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ms.dao.CartDao;
import ms.dao.UserDao;
import ms.model.Cart;
import ms.service.CartManager;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

@Service("cartManager")
@RemotingDestination(channels = { "my-amf" })
public class CartManagerImpl implements CartManager {
	private CartDao cartdao;
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public CartDao getCartdao() {
		return cartdao;
	}

	@Resource
	public void setCartdao(CartDao cartdao) {
		this.cartdao = cartdao;
	}

	@RemotingInclude
	public String add(Cart cart) throws Exception {
		cartdao.save(cart);

		return "succeed";

	}

	@RemotingInclude
	public int deleletByid(int cart_id) throws Exception {
		return cartdao.deleletByid(cart_id);
	}

	@RemotingInclude
	public String modify(Cart cart) throws Exception {
		
		cartdao.modify(cart);
		return "succeed";
	}

	@RemotingInclude
	public List<Cart> getCarts(String[] paramter, int start, int limit, int type)
			throws Exception {
		if (type == 1) {
			return cartdao.getCartsByNum(paramter[0], start, limit);
		}
		if (type == 2) {
			return cartdao.getCartsByState(paramter[0], start, limit);
		}
		if (type == 3) {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			Date date1 = formatter.parse(paramter[1], pos);
			formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			pos = new ParsePosition(0);
			Date date2 = formatter.parse(paramter[2], pos);
			return cartdao.getCartsByDate(date1, date2, start, limit);
		}
		if (type == 4) {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			Date date1 = formatter.parse(paramter[1], pos);
			Date date2 = formatter.parse(paramter[2], pos);
			return cartdao.getCartsByDateAndState(date1, date2, paramter[0],
					start, limit);
		} else {
			return cartdao.getCarts(start, limit);
		}

	}

	@RemotingInclude
	public Cart loadById(int cart_id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@RemotingInclude
	public int getTotalNum(String[] paramter, int type) throws Exception {
		if (type == 1) {
			return cartdao.getTotalNumByNum(paramter[0]);
		}
		if (type == 2) {
			return cartdao.getTotalNumByState(paramter[0]);
		}
		if (type == 3) {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			Date date1 = formatter.parse(paramter[1], pos);
			formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			pos = new ParsePosition(0);
			Date date2 = formatter.parse(paramter[2], pos);
			return cartdao.getTotalNumByDate(date1, date2);
		}
		if (type == 4) {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			Date date1 = formatter.parse(paramter[1], pos);
			Date date2 = formatter.parse(paramter[2], pos);
			return cartdao.getTotalNumByDateAndState(date1, date2, paramter[0]);
		} else {
			return cartdao.getTotalNum();
		}
	}

	public Cart loadByNum(String num) {
		// TODO Auto-generated method stub
		return cartdao.loadByNum(num);
	}

	public List<Cart> getCartsByUid(int uid, int start, int limit)
			throws Exception {
		// TODO Auto-generated method stub
		return cartdao.getCartsByUid(uid, start, limit);
	}

}
