package ms.service.impl;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

import ms.dao.PorderDao;
import ms.model.PayType;
import ms.model.Porder;
import ms.service.PorderManager;

@Service("porderManager")
@RemotingDestination(channels = { "my-amf" })
public class PorderManagerImpl implements PorderManager {
	private PorderDao proderDao;

	public PorderDao getProderDao() {
		return proderDao;
	}

	@Resource
	public void setProderDao(PorderDao proderDao) {
		this.proderDao = proderDao;
	}

	@RemotingInclude
	public String save(Porder porder) {

		proderDao.save(porder);
		
		return "succeed";

	}

	@RemotingInclude
	public int deleletByid(int id) {

		return proderDao.deleletByid(id);
	}

	@RemotingInclude
	public String modify(Porder porder) {
		proderDao.modify(porder);
		return "succeed";

	}

	@RemotingInclude
	public List<Porder> getPorders(int start, int limit) {
		// TODO Auto-generated method stub
		return proderDao.getPorders(start, limit);
	}

	@RemotingInclude
	public List<Porder> getPordersByNum(String num, int start, int limit) {
		// TODO Auto-generated method stub
		return proderDao.getPordersByNum(num, start, limit);
	}

	@RemotingInclude
	public List<Porder> getPordersByDate(String date11, String date22,
			int start, int limit) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date1 = formatter.parse(date11, pos);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pos = new ParsePosition(0);
		Date date2 = formatter.parse(date22, pos);
		return proderDao.getPordersByDate(date1, date2, start, limit);
	}

	@RemotingInclude
	public int getTotalNum() {
		// TODO Auto-generated method stub
		return proderDao.getTotalNum();
	}

	@RemotingInclude
	public int getTotalNumByNum(String num) {
		// TODO Auto-generated method stub
		return proderDao.getTotalNumByNum(num);
	}

	@RemotingInclude
	public int getTotalNumByDate(String date11, String date22) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date1 = formatter.parse(date11, pos);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pos = new ParsePosition(0);
		Date date2 = formatter.parse(date22, pos);
		return proderDao.getTotalNumByDate(date1, date2);
	}

	@RemotingInclude
	public Porder loadByNum(String num) {
		// TODO Auto-generated method stub
		return proderDao.loadByNum(num);
	}

}
