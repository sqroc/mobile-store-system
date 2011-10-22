package ms.dao;

import java.util.Date;
import java.util.List;

import ms.model.PorderDetail;


public interface PorderDetailDao {

	public void save(PorderDetail porderDetail);

	public int deleletByid(int id);

	public void modify(PorderDetail porderDetail);

	public List<PorderDetail> getPorderDetails(int start, int limit);

	public List<PorderDetail> getPorderDetailsByNum(String num, int start, int limit);
	
	public List<PorderDetail> getPorderDetailsByNum(String num);
	
	public List<PorderDetail> getPorderDetailsByDate(Date date1, Date date2, int start,
			int limit);

	public int getTotalNum();

	public int getTotalNumByNum(String num);

	public int getTotalNumByDate(Date date1, Date date2);

	public PorderDetail loadByNum(String num);
	
	public List<PorderDetail> loadByNums(String num);
}
