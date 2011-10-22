package ms.service;

import java.util.Date;
import java.util.List;

import ms.model.StoreInOut;

public interface StoreInOutManager {

	public List GetlistbyMoneyandDate(String date11, String date22,int type);
	
	public List<StoreInOut> getStoreInOutByDate(String date1,String date2);
	public List<StoreInOut> getStoreInOutByDate(String date11, String date22,
			int start, int limit);
	public String save(StoreInOut storeInOut);

	public int deleletByid(int id);

	public void modify(StoreInOut storeInOut);
	
	public List<StoreInOut> getStoreInOuts(int start, int limit);
	
	public List<StoreInOut> getAllStoreInOuts();
	
	public int getTotalNum();
	
	public int getTotalNumByDate(String date11, String date22);
	
}
