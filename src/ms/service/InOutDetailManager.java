package ms.service;

import java.util.Date;
import java.util.List;

import ms.model.InOutDetail;

public interface InOutDetailManager {

	public List GetlistbyCategory(String date11,String date22);
	
	public List GetlistbyItem(String date11,String date22,String name,int type);
	
	public List GetlistDetailbyItem(String date11,String date22,String name);
	
	public void save(InOutDetail inOutDetail);

	public int deleletByid(int id);

	public void modify(InOutDetail inOutDetail);
	
	public List<InOutDetail> getInOutDetails(int start, int limit);
	
	public int getTotalNum();
}
