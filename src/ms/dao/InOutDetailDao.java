package ms.dao;

import java.util.Date;
import java.util.List;

import ms.model.InOutDetail;


public interface InOutDetailDao {
	public void save(InOutDetail inOutDetail);

	public int deleletByid(int id);

	public void modify(InOutDetail inOutDetail);
	
	public List<InOutDetail> getInOutDetails(int start, int limit);
	
	public List<InOutDetail> getInOutDetailsByDate(Date date1,Date date2);
	
	public List<InOutDetail> getInOutDetailsByDate(Date date1,Date date2,int item_id);
	
	public int getInOutDetailsByDateAndCategory(Date date1,Date date2,int cid);
	
	public int getInOutDetailsByDateAndItem(Date date1,Date date2,int item_id);
	
	public int getTotalNum();

}
