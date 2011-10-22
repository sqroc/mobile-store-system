package ms.service;

import java.util.List;

import ms.model.BackDetail;

public interface BackDetailManager {
	public void save(BackDetail backDetail);

	public int deleletByid(int id);

	public void modify(BackDetail backDetail);

	public List<BackDetail> getBackDetails(int start, int limit);

	public List<BackDetail> getBackDetailsByNum(String num, int start, int limit);

	public List<BackDetail> getBackDetailsByDate(String date11, String date22,
			int start, int limit);

	public int getTotalNum();

	public int getTotalNumByNum(String num);

	public int getTotalNumByDate(String date11, String date22);

	public BackDetail loadByNum(String num);
}
