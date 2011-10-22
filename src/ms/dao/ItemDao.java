package ms.dao;

import java.util.List;

import ms.model.Item;
import ms.model.User;


public interface ItemDao {
	public void save(Item item);

	public int deleletByid(int id,int ipid);

	public void modify(Item item);
	
	public List<Item> getItems(int start, int limit);
	
	public List<Item> getItemsByName(String name,int start, int limit);
	
	public List<Item> getItemsBySNum(int store_num,int start, int limit);
	
	public List<Item> getItemsByCid(int cid,int start, int limit);
	
	public int getTotalNum();

	public int getTotalNumByName(String name);
	
	public int getTotalNumBySNum(int store_num);

	public Item loadById(int id);

	public List<Item> loadByName(String name,int start, int limit) throws Exception;
	
	public Item loadByPnum(String pnum);
	
	

}
