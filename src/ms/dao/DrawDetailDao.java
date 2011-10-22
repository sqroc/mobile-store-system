package ms.dao;

import java.util.Date;
import java.util.List;

import ms.model.DrawDetail;

public interface DrawDetailDao {
	public void save(DrawDetail drawDetail);

	public int deleletByid(int id);

	public void modify(DrawDetail drawDetail);

	public List<DrawDetail> getDrawDetails(int start, int limit);
	
	public List<DrawDetail> getDrawDetailsByNum(String num);

	public List<DrawDetail> getDrawDetailsByNum(String num, int start, int limit);

	public List<DrawDetail> getDrawDetailsByDate(Date date1, Date date2,
			int start, int limit);

	public int getTotalNum();

	public int getTotalNumByNum(String num);

	public int getTotalNumByDate(Date date1, Date date2);

	public DrawDetail loadByNum(String num);
}
