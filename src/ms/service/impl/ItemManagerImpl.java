package ms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import ms.dao.CategoryDao;
import ms.dao.ItemDao;
import ms.dao.ItemProviderDao;
import ms.dao.ProviderDao;
import ms.model.Category;
import ms.model.Item;
import ms.model.ItemProvider;
import ms.model.Provider;
import ms.service.ItemManager;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

@Service("itemManager")
@RemotingDestination(channels = { "my-amf" })
public class ItemManagerImpl implements ItemManager {
	private ItemDao itemDao;
	private CategoryDao categoryDao;
	private ItemProviderDao itemProviderDao;
	private ProviderDao providerDao;

	public ProviderDao getProviderDao() {
		return providerDao;
	}

	@Resource
	public void setProviderDao(ProviderDao providerDao) {
		this.providerDao = providerDao;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public ItemProviderDao getItemProviderDao() {
		return itemProviderDao;
	}

	@Resource
	public void setItemProviderDao(ItemProviderDao itemProviderDao) {
		this.itemProviderDao = itemProviderDao;
	}

	@Resource
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	@Resource
	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	@RemotingInclude
	public String add(Item item) throws Exception {

		Category tempCategory = categoryDao.loadByName(item.getCategoryName());
		Provider tempProvider = providerDao.loadByName(item.getProviderName());

		item.setCategory(tempCategory);

		ItemProvider newItemProvider = new ItemProvider();

		newItemProvider.setItem(item);
		newItemProvider.setProvider(tempProvider);
		newItemProvider.setCost(item.getCost());
		newItemProvider.setPrice(item.getPrice());

		itemProviderDao.save(newItemProvider);
		// itemDao.save(item);

		return "succeed";

	}

	@RemotingInclude
	public int deleletByid(int item_id, int ipid) throws Exception {
		// TODO Auto-generated method stub
		return itemDao.deleletByid(item_id, ipid);
	}

	@RemotingInclude
	public String modify(Item item) throws Exception {

		Category tempCategory = categoryDao.loadByName(item.getCategoryName());
		item.setCategory(tempCategory);

		ItemProvider itemProvider = itemProviderDao.loadbyItemid(item
				.getItem_id());

		itemProvider.setCost(item.getCost());
		itemProvider.setPrice(item.getPrice());

		itemProviderDao.modify(itemProvider);
		itemDao.modify(item);

		return "succeed";

	}

	@RemotingInclude
	public List<Item> getItems(String paramter, int start, int limit, int type)
			throws Exception {
		if (type == 1) {
			return itemDao.getItemsByName(paramter, start, limit);
		}
		if (type == 2) {
			int paramter2 = Integer.parseInt(paramter);
			return itemDao.getItemsBySNum(paramter2, start, limit);
		}
		if (type == 3) {
			int paramter2 = Integer.parseInt(paramter);
			return itemDao.getItemsByCid(paramter2, start, limit);
		} else {
			return itemDao.getItems(start, limit);
		}

	}

	@RemotingInclude
	public Item loadById(int item_id) throws Exception {
		// TODO Auto-generated method stub
		return itemDao.loadById(item_id);
	}

	@RemotingInclude
	public int getTotalNum(String paramter, int type) throws Exception {
		if (type == 1) {
			return itemDao.getTotalNumByName(paramter);
		}
		if (type == 2) {
			int paramter2 = Integer.parseInt(paramter);
			return itemDao.getTotalNumBySNum(paramter2);
		} else {
			return itemDao.getTotalNum();
		}

	}

	public List<Item> loadByName(String name, int start, int limit)
			throws Exception {
		// TODO Auto-generated method stub
		return itemDao.loadByName(name, start, limit);
	}

	@RemotingInclude
	public Item loadByPnum(String pnum) {
		// TODO Auto-generated method stub
		return itemDao.loadByPnum(pnum);
	}

}
