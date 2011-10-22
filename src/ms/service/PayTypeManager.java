package ms.service;

import java.util.List;

import ms.model.PayType;


public interface PayTypeManager {
	public abstract String add(PayType payType) throws Exception;

	public abstract int deleletByid(int id) throws Exception;

	public abstract String modify(PayType payType) throws Exception;

	public List<PayType> getPayTypes(int start ,int limit) throws Exception;

	public PayType loadById(int id) throws Exception;
	
	public PayType loadByName(String name) throws Exception;
	
	public int getTotalNum() throws Exception;
	
	public List<String> getNames() throws Exception;
	
}
