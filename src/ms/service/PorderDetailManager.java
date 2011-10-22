package ms.service;

import java.util.List;

import ms.model.PorderDetail;


public interface PorderDetailManager {

	public String save(PorderDetail porderDetail);

	public int deleletByid(int id);

	public void modify(PorderDetail porderDetail);

	public List<PorderDetail> getPorderDetails(int start, int limit);
	
	public List<PorderDetail> getPorderDetailsByNum(String num);

	public List<PorderDetail> getPorderDetailsByNum(String num, int start, int limit);

	public List<PorderDetail> getPorderDetailsByDate(String date11, String date22, int start,
			int limit);

	public int getTotalNum();

	public int getTotalNumByNum(String num);

	public int getTotalNumByDate(String date11, String date22);

	public PorderDetail loadByNum(String num);
}
