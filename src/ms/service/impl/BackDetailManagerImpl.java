package ms.service.impl;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ms.dao.BackDetailDao;
import ms.model.BackDetail;
import ms.service.BackDetailManager;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

@Service("backDetailManager")
@RemotingDestination(channels = { "my-amf" })
public class BackDetailManagerImpl implements BackDetailManager {
	private BackDetailDao backDetailDao;

	public BackDetailDao getBackDetailDao() {
		return backDetailDao;
	}

	@Resource
	public void setBackDetailDao(BackDetailDao backDetailDao) {
		this.backDetailDao = backDetailDao;
	}

	@RemotingInclude
	public void save(BackDetail backDetail) {
		backDetailDao.save(backDetail);

	}

	@RemotingInclude
	public int deleletByid(int id) {
		// TODO Auto-generated method stub
		return backDetailDao.deleletByid(id);
	}

	@RemotingInclude
	public void modify(BackDetail backDetail) {
		backDetailDao.modify(backDetail);

	}

	@RemotingInclude
	public List<BackDetail> getBackDetails(int start, int limit) {
		// TODO Auto-generated method stub
		return backDetailDao.getBackDetails(start, limit);
	}

	@RemotingInclude
	public List<BackDetail> getBackDetailsByNum(String num, int start, int limit) {
		// TODO Auto-generated method stub
		return backDetailDao.getBackDetailsByNum(num, start, limit);
	}

	@RemotingInclude
	public List<BackDetail> getBackDetailsByDate(String date11, String date22,
			int start, int limit) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date1 = formatter.parse(date11, pos);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pos = new ParsePosition(0);
		Date date2 = formatter.parse(date22, pos);
		return backDetailDao.getBackDetailsByDate(date1, date2, start, limit);
	}

	@RemotingInclude
	public int getTotalNum() {
		// TODO Auto-generated method stub
		return backDetailDao.getTotalNum();
	}

	@RemotingInclude
	public int getTotalNumByNum(String num) {
		// TODO Auto-generated method stub
		return backDetailDao.getTotalNumByNum(num);
	}

	@RemotingInclude
	public int getTotalNumByDate(String date11, String date22) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date1 = formatter.parse(date11, pos);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pos = new ParsePosition(0);
		Date date2 = formatter.parse(date22, pos);
		return backDetailDao.getTotalNumByDate(date1, date2);
	}

	@RemotingInclude
	public BackDetail loadByNum(String num) {
		// TODO Auto-generated method stub
		return backDetailDao.loadByNum(num);
	}

}
