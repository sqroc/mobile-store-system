package ms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import ms.dao.ItemDao;
import ms.dao.ItemProviderDao;
import ms.dao.ProviderDao;
import ms.model.Item;
import ms.model.ItemProvider;
import ms.model.Provider;
import ms.service.ItemProviderManager;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

@Service("itemProviderManager")
@RemotingDestination(channels = { "my-amf" })
public class ItemProviderManagerImpl implements ItemProviderManager {

	private ItemProviderDao itemProviderDao;
	private ProviderDao  providerDao;
	private ItemDao itemDao;
	
	
	public ProviderDao getProviderDao() {
		return providerDao;
	}
	@Resource
	public void setProviderDao(ProviderDao providerDao) {
		this.providerDao = providerDao;
	}
	public ItemDao getItemDao() {
		return itemDao;
	}
	@Resource
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}
	public ItemProviderDao getItemProviderDao() {
		return itemProviderDao;
	}
	@Resource
	public void setItemProviderDao(ItemProviderDao itemProviderDao) {
		this.itemProviderDao = itemProviderDao;
	}
	@RemotingInclude
	public String add(ItemProvider itemProvider) throws Exception {
		itemProviderDao.save(itemProvider);
		
		return "succeed";
	}
	@RemotingInclude
	public int deleletByid(int ipid) throws Exception {
		return itemProviderDao.deleletByid(ipid);
	}
	@RemotingInclude
	public String modify(ItemProvider itemProvider) throws Exception {
		
		
		Provider pro = providerDao.loadByAid(itemProvider.getPid());
				
		itemProvider.setProvider(pro);
		
		Item item = itemDao.loadById(itemProvider.getItid());
		
				itemProvider.setItem(item);
		itemProviderDao.modify(itemProvider);
		return "succeed";
	}
	@RemotingInclude
	public List<ItemProvider> getItemProviders(int start, int limit,int itemId_ip)
			throws Exception {
		return itemProviderDao.getItemProviders(start, limit,itemId_ip);
	}
	@RemotingInclude
	public ItemProvider loadById(int ipid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@RemotingInclude
	public int getTotalNum(int itemId_ip) throws Exception {
		return itemProviderDao.getTotalNum( itemId_ip );
	}
	@RemotingInclude
	public ItemProvider loadbyItemid(int id) {
		// TODO Auto-generated method stub
		return itemProviderDao.loadbyItemid(id);
	}
	
}
