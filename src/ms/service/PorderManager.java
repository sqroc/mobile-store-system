package ms.service;

import java.util.Date;
import java.util.List;

import ms.model.Porder;

public interface PorderManager {

	public String save(Porder porder);

	public int deleletByid(int id);

	public String modify(Porder porder);

	public List<Porder> getPorders(int start, int limit);

	public List<Porder> getPordersByNum(String num, int start, int limit);

	public List<Porder> getPordersByDate(String date11, String date22, int start,
			int limit);

	public int getTotalNum();

	public int getTotalNumByNum(String num);

	public int getTotalNumByDate(String date11, String date22);

	public Porder loadByNum(String num);
}
