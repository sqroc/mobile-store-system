package ms.dao;

import java.util.List;

import ms.model.ItemProvider;


public interface ItemProviderDao {

	public void save(ItemProvider itemProvider);

	public int deleletByid(int id);

	public void modify(ItemProvider itemProvider);
	
	public List<ItemProvider> getItemProviders(int start, int limit,int itemId_ip);
	
	public int getTotalNum(int itemId_ip);
	
	public ItemProvider loadbyItemid(int id);
}
