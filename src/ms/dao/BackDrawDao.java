package ms.dao;

import java.util.Date;
import java.util.List;

import ms.model.BackDraw;


public interface BackDrawDao {

	public void save(BackDraw backDraw);

	public int deleletByid(int id);

	public void modify(BackDraw backDraw);

	public List<BackDraw> getBackDraws(int start, int limit);

	public List<BackDraw> getBackDrawsByNum(String num, int start, int limit);

	public List<BackDraw> getBackDrawsByDate(Date date1, Date date2, int start,
			int limit);

	public int getTotalNum();

	public int getTotalNumByNum(String num);

	public int getTotalNumByDate(Date date1, Date date2);

	public BackDraw loadByNum(String num);
}
