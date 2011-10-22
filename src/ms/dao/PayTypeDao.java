package ms.dao;

import java.util.List;

import ms.model.PayType;


public interface PayTypeDao {
	public void save(PayType payType);

	public int deleletByid(int id);

	public void modify(PayType payType);
	
	public List<PayType> getPayTypes(int start, int limit);
	
	public List<PayType> getAllPayTypes();
	
	public List<String> getNames();
	
	public int getTotalNum();

	public PayType loadByName(String name) throws Exception;
	
	public List<PayType> loadByNames(String name) throws Exception;

}
