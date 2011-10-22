package ms.service;

import java.util.List;

import ms.model.Item;
import ms.model.User;


public interface ItemManager {

	public abstract String add(Item item) throws Exception;

	public abstract int deleletByid(int item_id,int ipid) throws Exception;

	public abstract String modify(Item item) throws Exception;

	public List<Item> getItems(String paramter,int start ,int limit,int type) throws Exception;

	public Item loadById(int item_id) throws Exception;
	
	public int getTotalNum(String paramter,int type) throws Exception;
	
	public List<Item> loadByName(String name,int start, int limit) throws Exception;
	
	public Item loadByPnum(String pnum);
}
