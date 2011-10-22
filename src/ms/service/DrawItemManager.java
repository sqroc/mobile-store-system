package ms.service;

import java.util.Date;
import java.util.List;

import ms.model.DrawItem;

public interface DrawItemManager {

	public String save(DrawItem drawItem);

	public int deleletByid(int id);

	public void modify(DrawItem drawItem);

	public List<DrawItem> getDrawItems(int start, int limit);

	public List<DrawItem> getDrawItemsByNum(String num, int start, int limit);

	public List<DrawItem> getDrawItemsByDate(String date11, String date22, int start,
			int limit);

	public int getTotalNum();

	public int getTotalNumByNum(String num);

	public int getTotalNumByDate(String date11, String date22);

	public DrawItem loadByNum(String num);
}
