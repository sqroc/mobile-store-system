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
import ms.dao.PorderDetailDao;
import ms.model.PorderDetail;
import ms.service.PorderDetailManager;

@Service("porderDetailManager")
@RemotingDestination(channels = { "my-amf" })
public class PorderDetailManagerImpl implements PorderDetailManager {
	private PorderDetailDao proderDetailDao;

	public PorderDetailDao getProderDetailDao() {
		return proderDetailDao;
	}

	@Resource
	public void setProderDetailDao(PorderDetailDao proderDetailDao) {
		this.proderDetailDao = proderDetailDao;
	}

	@RemotingInclude
	public String  save(PorderDetail porderDetail) {
		proderDetailDao.save(porderDetail);
		return "succeed";
	}

	@RemotingInclude
	public int deleletByid(int id) {
		// TODO Auto-generated method stub
		return proderDetailDao.deleletByid(id);
	}

	@RemotingInclude
	public void modify(PorderDetail porderDetail) {
		proderDetailDao.modify(porderDetail);

	}

	@RemotingInclude
	public List<PorderDetail> getPorderDetails(int start, int limit) {
		// TODO Auto-generated method stub
		return proderDetailDao.getPorderDetails(start, limit);
	}

	@RemotingInclude
	public List<PorderDetail> getPorderDetailsByNum(String num, int start,
			int limit) {
		// TODO Auto-generated method stub
		return proderDetailDao.getPorderDetailsByNum(num, start, limit);
	}

	public List<PorderDetail> getPorderDetailsByNum(String num) {
		// TODO Auto-generated method stub
		return proderDetailDao.getPorderDetailsByNum(num);
	}

	@RemotingInclude
	public List<PorderDetail> getPorderDetailsByDate(String date11,
			String date22, int start, int limit) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date1 = formatter.parse(date11, pos);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pos = new ParsePosition(0);
		Date date2 = formatter.parse(date22, pos);
		return proderDetailDao.getPorderDetailsByDate(date1, date2, start,
				limit);
	}

	@RemotingInclude
	public int getTotalNum() {
		// TODO Auto-generated method stub
		return proderDetailDao.getTotalNum();
	}

	@RemotingInclude
	public int getTotalNumByNum(String num) {
		// TODO Auto-generated method stub
		return proderDetailDao.getTotalNumByNum(num);
	}

	@RemotingInclude
	public int getTotalNumByDate(String date11, String date22) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date1 = formatter.parse(date11, pos);
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		pos = new ParsePosition(0);
		Date date2 = formatter.parse(date22, pos);
		return proderDetailDao.getTotalNumByDate(date1, date2);
	}

	@RemotingInclude
	public PorderDetail loadByNum(String num) {
		// TODO Auto-generated method stub
		return proderDetailDao.loadByNum(num);
	}

}
