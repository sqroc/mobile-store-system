package ms.dao;

import java.util.List;

import ms.model.Storage;


public interface StorageDao {
	public void save(Storage storage);

	public int deleletByid(int id);

	public void modify(Storage storage);
	
	public List<Storage> getStorages(int start, int limit);
	
	public int getTotalNum();

}
