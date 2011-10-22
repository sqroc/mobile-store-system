package ms.dao;

import java.util.Date;
import java.util.List;

import ms.model.Cart;
import ms.model.StoreInOut;

public interface StoreInOutDao {

	public double getTotalMByDate(Date date1,Date date2);
	
	public List<StoreInOut> getStoreInOutByDate(Date date1,Date date2);
	
	public void save(StoreInOut storeInOut);

	public int deleletByid(int id);

	public void modify(StoreInOut storeInOut);
	
	public List<StoreInOut> getStoreInOuts(int start, int limit);
	
	public List<StoreInOut> getAllStoreInOuts();
	
	public int getTotalNum();
	
	public int getTotalNumByDate(Date date1, Date date2);
	
	public List<StoreInOut> getStoreInOutByDate(Date date1,Date date2,int start, int limit);

}
