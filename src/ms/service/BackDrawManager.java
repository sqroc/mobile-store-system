package ms.service;

import java.util.Date;
import java.util.List;

import ms.model.BackDraw;

public interface BackDrawManager {
	public void save(BackDraw backDraw);

	public int deleletByid(int id);

	public String modify(BackDraw backDraw);

	public List<BackDraw> getBackDraws(int start, int limit);

	public List<BackDraw> getBackDrawsByNum(String num, int start, int limit);

	public List<BackDraw> getBackDrawsByDate(String date11, String date22, int start,
			int limit);

	public int getTotalNum();

	public int getTotalNumByNum(String num);

	public int getTotalNumByDate(String date11, String date22);

	public BackDraw loadByNum(String num);
}
