package ms.dao;

import java.util.Date;
import java.util.List;

import ms.model.BackDetail;

public interface BackDetailDao {
	public void save(BackDetail backDetail);

	public int deleletByid(int id);

	public void modify(BackDetail backDetail);

	public List<BackDetail> getBackDetails(int start, int limit);

	public List<BackDetail> getBackDetailsByNum(String num, int start, int limit);
	
	public List<BackDetail> getBackDetailsByNum(String num);

	public List<BackDetail> getBackDetailsByDate(Date date1, Date date2,
			int start, int limit);

	public int getTotalNum();

	public int getTotalNumByNum(String num);

	public int getTotalNumByDate(Date date1, Date date2);

	public BackDetail loadByNum(String num);
}
