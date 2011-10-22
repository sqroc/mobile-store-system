package ms.service.impl;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ms.dao.CartDao;
import ms.dao.DrawDetailDao;
import ms.dao.DrawItemDao;
import ms.dao.OrderItemDao;
import ms.model.Cart;
import ms.model.DrawDetail;
import ms.model.DrawItem;
import ms.model.OrderItem;
import ms.service.DrawItemManager;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

@Service("drawItemManager")
@RemotingDestination(channels = { "my-amf" })
public class DrawItemManagerImpl implements DrawItemManager {
	private DrawItemDao drawItemDao;
	private CartDao cartDao;
	private OrderItemDao orderItemDao;
	private DrawDetailDao drawDetailDao;

	public DrawItemDao getDrawItemDao() {
		return drawItemDao;
	}

	@Resource
	public void setDrawItemDao(DrawItemDao drawItemDao) {
		this.drawItemDao = drawItemDao;
	}

	public CartDao getCartDao() {
		return cartDao;
	}

	@Resource
	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	public OrderItemDao getOrderItemDao() {
		return orderItemDao;
	}

	@Resource
	public void setOrderItemDao(OrderItemDao orderItemDao) {
		this.orderItemDao = orderItemDao;
	}

	public DrawDetailDao getDrawDetailDao() {
		return drawDetailDao;
	}

	@Resource
	public void setDrawDetailDao(DrawDetailDao drawDetailDao) {
		this.drawDetailDao = drawDetailDao;
	}

	@RemotingInclude
	public String save(DrawItem drawItem) {
		drawItemDao.save(drawItem);
		Cart cart = cartDao.loadByNum(drawItem.getCode());
		List<OrderItem> orderItems = orderItemDao.getOrderItembyCart(cart);
		for (int i = 0; i < orderItems.size(); i++) {
			DrawDetail drawDetail = new DrawDetail();
			drawDetail.setDrawItem(drawItem);
			drawDetail.setCode(drawItem.getCode());
			drawDetail.setItem(orderItems.get(i).getItem());
			drawDetail.setQuantity(orderItems.get(i).getNumber());
			drawDetail.setAprice(orderItems.get(i).getPrice());
			drawDetailDao.save(drawDetail);
		}
		return "succeed";
	}

	@RemotingInclude
	public int deleletByid(int id) {
		// TODO Auto-generated method stub
		return drawItemDao.deleletByid(id);
	}

	@RemotingInclude
	public void modify(DrawItem drawItem) {
		drawItemDao.modify(drawItem);

	}

	@RemotingInclude
	public List<DrawItem> getDrawItems(int start, int limit) {
		// TODO Auto-generated method stub
		return drawItemDao.getDrawItems(start, limit);
	}

	@RemotingInclude
	public List<DrawItem> getDrawItemsByNum(String num, int start, int limit) {
		// TODO Auto-generated method stub
		return drawItemDao.getDrawItemsByNum(num, start, limit);
	}

	@RemotingInclude
	public List<DrawItem> getDrawItemsByDate(String date11, String date22,
			int start, int limit) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date1 = formatter.parse(date11, pos);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pos = new ParsePosition(0);
		Date date2 = formatter.parse(date22, pos);
		return drawItemDao.getDrawItemsByDate(date1, date2, start, limit);
	}

	@RemotingInclude
	public int getTotalNum() {
		// TODO Auto-generated method stub
		return drawItemDao.getTotalNum();
	}

	@RemotingInclude
	public int getTotalNumByNum(String num) {
		// TODO Auto-generated method stub
		return drawItemDao.getTotalNumByNum(num);
	}

	@RemotingInclude
	public int getTotalNumByDate(String date11, String date22) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date1 = formatter.parse(date11, pos);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pos = new ParsePosition(0);
		Date date2 = formatter.parse(date22, pos);
		return drawItemDao.getTotalNumByDate(date1, date2);
	}

	@RemotingInclude
	public DrawItem loadByNum(String num) {
		// TODO Auto-generated method stub
		return drawItemDao.loadByNum(num);
	}

}
