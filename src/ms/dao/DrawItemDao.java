package ms.dao;

import java.util.Date;
import java.util.List;

import ms.model.DrawItem;


public interface DrawItemDao {

	public void save(DrawItem drawItem);

	public int deleletByid(int id);

	public void modify(DrawItem drawItem);

	public List<DrawItem> getDrawItems(int start, int limit);

	public List<DrawItem> getDrawItemsByNum(String num, int start, int limit);

	public List<DrawItem> getDrawItemsByDate(Date date1, Date date2, int start,
			int limit);

	public int getTotalNum();

	public int getTotalNumByNum(String num);

	public int getTotalNumByDate(Date date1, Date date2);

	public DrawItem loadByNum(String num);
}
