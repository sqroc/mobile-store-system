package ms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import ms.dao.CartinfoDao;
import ms.model.Cartinfo;
import ms.service.CartinfoManager;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

@Service("cartinfoManager")
@RemotingDestination(channels = { "my-amf" })
public class CartinfoManagerImpl implements CartinfoManager {

	private CartinfoDao cartinfoDao;

	public CartinfoDao getCartinfoDao() {
		return cartinfoDao;
	}

	@Resource
	public void setCartinfoDao(CartinfoDao cartinfoDao) {
		this.cartinfoDao = cartinfoDao;
	}

	@RemotingInclude
	public String add(Cartinfo cartinfo) throws Exception {
		cartinfoDao.save(cartinfo);

		return "succeed";
	}

	@RemotingInclude
	public int deleletByid(int id) throws Exception {
		// TODO Auto-generated method stub
		return cartinfoDao.deleletByid(id);
	}

	@RemotingInclude
	public String modify(Cartinfo cartinfo) throws Exception {
		cartinfoDao.modify(cartinfo);
		return "succeed";
	}

	@RemotingInclude
	public List<Cartinfo> getCartinfos(int start, int limit, int uid) throws Exception {
		return cartinfoDao.getCartinfos(start, limit, uid);
	}

	@RemotingInclude
	public List<Cartinfo> loadbyuid(int uid) throws Exception {
		// TODO Auto-generated method stub
		return cartinfoDao.loadbyuid(uid);
	}
	@RemotingInclude
	public int deleletByUid(int id) {
		// TODO Auto-generated method stub
		return cartinfoDao.deleletByUid(id);
	}

}
