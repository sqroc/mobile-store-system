package ms.service;

import java.util.List;

import ms.model.ItemProvider;
import ms.model.Provider;

public interface ItemProviderManager {

	public abstract String add(ItemProvider itemProvider) throws Exception;

	public abstract int deleletByid(int ipid) throws Exception;

	public abstract String modify(ItemProvider ItemProvider) throws Exception;

	public List<ItemProvider> getItemProviders(int start ,int limit,int itemId_ip) throws Exception;

	public ItemProvider loadById(int ipid) throws Exception;
	
	public int getTotalNum(int itemId_ip) throws Exception;
	
	public ItemProvider loadbyItemid(int id);

}
