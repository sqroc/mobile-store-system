package ms.dao;

import java.util.Date;
import java.util.List;

import ms.model.Porder;

public interface PorderDao {

	public void save(Porder porder);

	public int deleletByid(int id);

	public void modify(Porder porder);

	public List<Porder> getPorders(int start, int limit);

	public List<Porder> getPordersByNum(String num, int start, int limit);

	public List<Porder> getPordersByDate(Date date1, Date date2, int start,
			int limit);

	public int getTotalNum();

	public int getTotalNumByNum(String num);

	public int getTotalNumByDate(Date date1, Date date2);

	public Porder loadByNum(String num);
}
