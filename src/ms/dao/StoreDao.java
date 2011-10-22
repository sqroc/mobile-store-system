package ms.dao;

import java.util.List;

import ms.model.Store;

public interface StoreDao {

	public void save(Store store);

	public int deleletByid(int id);

	public void modify(Store store);

	public List<Store> getStores(int start, int limit);

	public List<Store> getAllStores();

	public List<String> getNames();

	public int getTotalNum();

	public Store loadByName(String name) throws Exception;

	public List<Store> loadByNames(String name) throws Exception;
}
