package ms.service.impl;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ms.dao.BackDrawDao;
import ms.dao.UserDao;
import ms.model.BackDraw;
import ms.model.User;
import ms.service.BackDrawManager;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

@Service("backDrawManager")
@RemotingDestination(channels = { "my-amf" })
public class BackDrawManagerImpl implements BackDrawManager {
	private BackDrawDao backDrawDao;
	private UserDao userDao;

	public BackDrawDao getBackDrawDao() {
		return backDrawDao;
	}

	@Resource
	public void setBackDrawDao(BackDrawDao backDrawDao) {
		this.backDrawDao = backDrawDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@RemotingInclude
	public void save(BackDraw backDraw) {
		backDrawDao.save(backDraw);

	}

	@RemotingInclude
	public int deleletByid(int id) {
		// TODO Auto-generated method stub
		return backDrawDao.deleletByid(id);
	}

	@RemotingInclude
	public String modify(BackDraw backDraw) {
		if (backDraw.getState().equals("已处理")) {
			double money = backDraw.getTotalM();
			User user = backDraw.getUser();
			user.setMoney(user.getMoney() + money);
			userDao.modify(user);
		}
		backDrawDao.modify(backDraw);
		
		return "succeed";

	}

	@RemotingInclude
	public List<BackDraw> getBackDraws(int start, int limit) {
		// TODO Auto-generated method stub
		return backDrawDao.getBackDraws(start, limit);
	}

	@RemotingInclude
	public List<BackDraw> getBackDrawsByNum(String num, int start, int limit) {
		// TODO Auto-generated method stub
		return backDrawDao.getBackDrawsByNum(num, start, limit);
	}

	@RemotingInclude
	public List<BackDraw> getBackDrawsByDate(String date11, String date22,
			int start, int limit) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date1 = formatter.parse(date11, pos);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pos = new ParsePosition(0);
		Date date2 = formatter.parse(date22, pos);
		return backDrawDao.getBackDrawsByDate(date1, date2, start, limit);
	}

	@RemotingInclude
	public int getTotalNum() {
		// TODO Auto-generated method stub
		return backDrawDao.getTotalNum();
	}

	@RemotingInclude
	public int getTotalNumByNum(String num) {
		// TODO Auto-generated method stub
		return backDrawDao.getTotalNumByNum(num);
	}

	@RemotingInclude
	public int getTotalNumByDate(String date11, String date22) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date1 = formatter.parse(date11, pos);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pos = new ParsePosition(0);
		Date date2 = formatter.parse(date22, pos);
		return backDrawDao.getTotalNumByDate(date1, date2);
	}

	@RemotingInclude
	public BackDraw loadByNum(String num) {
		// TODO Auto-generated method stub
		return backDrawDao.loadByNum(num);
	}

}
