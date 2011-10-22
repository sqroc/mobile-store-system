package ms.service;

import java.util.Date;
import java.util.List;

import ms.model.DrawDetail;

public interface DrawDetailManager {
	public void save(DrawDetail drawDetail);

	public int deleletByid(int id);

	public void modify(DrawDetail drawDetail);

	public List<DrawDetail> getDrawDetails(int start, int limit);

	public List<DrawDetail> getDrawDetailsByNum(String num, int start, int limit);

	public List<DrawDetail> getDrawDetailsByDate(String date11, String date22,
			int start, int limit);

	public int getTotalNum();

	public int getTotalNumByNum(String num);

	public int getTotalNumByDate(String date11, String date22);

	public DrawDetail loadByNum(String num);
}
