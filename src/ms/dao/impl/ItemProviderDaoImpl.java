package ms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ms.dao.ItemProviderDao;
import ms.model.Item;
import ms.model.ItemProvider;

@Repository("ItemProviderDao")
public class ItemProviderDaoImpl implements ItemProviderDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public void save(ItemProvider itemProvider) {
		hibernateTemplate.save(itemProvider);

	}

	public int deleletByid(int id) {
		ItemProvider itemProvidert = new ItemProvider();
		itemProvidert.setIpid(id);
		try {
			hibernateTemplate.delete(itemProvidert);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}

	public void modify(ItemProvider itemProvider) {
		hibernateTemplate.update(itemProvider);

	}

	public List<ItemProvider> getItemProviders(final int start, final int limit,final int itemId_ip) {
		List<ItemProvider> itemProviders = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from ItemProvider where item_id="+itemId_ip);
						
						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<ItemProvider> list = (List<ItemProvider>) query.list();
						return list;
					}
				});
		

		if (itemProviders != null) {
			for (int i = 0; i < itemProviders.size(); i++) {
				String pname= (itemProviders.get(i).getProvider().getName());
				int pid = itemProviders.get(i).getProvider().getPid();
				itemProviders.get(i).setpName(pname);
				itemProviders.get(i).setPid(pid);
				
				String name =itemProviders.get(i).getItem().getItem_name();
				itemProviders.get(i).setItem_name(name);
				
				int itid = itemProviders.get(i).getItem().getItem_id();
                itemProviders.get(i).setItid(itid);
			}

		}
		
		
		
		return itemProviders;
	}

	public int getTotalNum(int itemId_ip) {
		String hql = "select count(*) from ItemProvider where item_id ="+itemId_ip;;
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public ItemProvider loadbyItemid(int id) {
		String queryString = "from  ItemProvider where item_id=" + id;

		List<ItemProvider> ips = hibernateTemplate.find(queryString);

		ItemProvider tempItemProvider =null;
		
		
		if(!ips.isEmpty())
		{
		  tempItemProvider  = ips.get(0);
		
		String pname= (tempItemProvider.getProvider().getName());
		tempItemProvider.setpName(pname);
		
		System.out.println(tempItemProvider.getIpid());
		System.out.println(tempItemProvider.getIpid());
		System.out.println(tempItemProvider.getIpid());
		System.out.println(tempItemProvider.getIpid());
		}
		return tempItemProvider;
	}
	

}
