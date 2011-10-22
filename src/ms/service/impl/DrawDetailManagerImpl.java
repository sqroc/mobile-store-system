package ms.service.impl;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ms.dao.DrawDetailDao;
import ms.model.DrawDetail;
import ms.service.DrawDetailManager;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

@Service("drawDetailManager")
@RemotingDestination(channels = { "my-amf" })
public class DrawDetailManagerImpl implements DrawDetailManager {
	private DrawDetailDao drawDetailDao;

	public DrawDetailDao getDrawDetailDao() {
		return drawDetailDao;
	}

	@Resource
	public void setDrawDetailDao(DrawDetailDao drawDetailDao) {
		this.drawDetailDao = drawDetailDao;
	}

	@RemotingInclude
	public void save(DrawDetail drawDetail) {
		drawDetailDao.save(drawDetail);
		

	}

	@RemotingInclude
	public int deleletByid(int id) {
		// TODO Auto-generated method stub
		return drawDetailDao.deleletByid(id);
	}

	@RemotingInclude
	public void modify(DrawDetail drawDetail) {
		drawDetailDao.modify(drawDetail);

	}

	@RemotingInclude
	public List<DrawDetail> getDrawDetails(int start, int limit) {
		// TODO Auto-generated method stub
		return drawDetailDao.getDrawDetails(start, limit);
	}

	@RemotingInclude
	public List<DrawDetail> getDrawDetailsByNum(String num, int start, int limit) {
		// TODO Auto-generated method stub
		return drawDetailDao.getDrawDetailsByNum(num, start, limit);
	}

	@RemotingInclude
	public List<DrawDetail> getDrawDetailsByDate(String date11, String date22,
			int start, int limit) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date1 = formatter.parse(date11, pos);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pos = new ParsePosition(0);
		Date date2 = formatter.parse(date22, pos);
		return drawDetailDao.getDrawDetailsByDate(date1, date2, start, limit);
	}

	@RemotingInclude
	public int getTotalNum() {
		// TODO Auto-generated method stub
		return drawDetailDao.getTotalNum();
	}

	@RemotingInclude
	public int getTotalNumByNum(String num) {
		// TODO Auto-generated method stub
		return drawDetailDao.getTotalNumByNum(num);
	}

	@RemotingInclude
	public int getTotalNumByDate(String date11, String date22) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date1 = formatter.parse(date11, pos);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pos = new ParsePosition(0);
		Date date2 = formatter.parse(date22, pos);
		return drawDetailDao.getTotalNumByDate(date1, date2);
	}

	@RemotingInclude
	public DrawDetail loadByNum(String num) {
		// TODO Auto-generated method stub
		return drawDetailDao.loadByNum(num);
	}

}
