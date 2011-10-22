package ms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import ms.dao.PayTypeDao;
import ms.model.Category;
import ms.model.PayType;
import ms.service.PayTypeManager;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

@Service("payTypeManager")
@RemotingDestination(channels = { "my-amf" })
public class PayTypeManagerImpl implements PayTypeManager {
	private PayTypeDao payTypeDao;

	public PayTypeDao getPayTypeDao() {
		return payTypeDao;
	}

	@Resource
	public void setPayTypeDao(PayTypeDao payTypeDao) {
		this.payTypeDao = payTypeDao;
	}
	@RemotingInclude
	public String add(PayType payType) throws Exception {
		List<PayType> payTypes = null;
		payTypes = payTypeDao.loadByNames(payType.getName());
		if(payTypes.size()!=0){
			return "failed";
		}else{
			payTypeDao.save(payType);
		return "succeed";
		}
	}
	@RemotingInclude
	public int deleletByid(int id) throws Exception {
		// TODO Auto-generated method stub
		return payTypeDao.deleletByid(id);
	}
	@RemotingInclude
	public String modify(PayType payType) throws Exception {
		//List<PayType> payTypes = null;
		//payTypes = payTypeDao.loadByNames(payType.getName());
		//if(payTypes.size()!=0){
		//	return "failed";
		//}else{
			payTypeDao.modify(payType);
		return "succeed";
		//}
	}
	@RemotingInclude
	public List<PayType> getPayTypes(int start, int limit) throws Exception {
		// TODO Auto-generated method stub
		return payTypeDao.getPayTypes(start, limit);
	}
	@RemotingInclude
	public PayType loadById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@RemotingInclude
	public PayType loadByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return payTypeDao.loadByName(name);
	}
	@RemotingInclude
	public int getTotalNum() throws Exception {
		// TODO Auto-generated method stub
		return payTypeDao.getTotalNum();
	}
	@RemotingInclude
	public List<String> getNames() throws Exception {
		// TODO Auto-generated method stub
		return payTypeDao.getNames();
	}

}
