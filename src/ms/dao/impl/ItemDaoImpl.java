package ms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ms.dao.ItemDao;
import ms.model.Category;
import ms.model.Item;
import ms.model.ItemProvider;

import ms.model.Provider;

import ms.model.User;

@Repository("ItemDao")
public class ItemDaoImpl implements ItemDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(Item item) {
		hibernateTemplate.save(item);

	}

	public int deleletByid(int id, int ipid) {

		Item itemtemp = new Item();
		itemtemp.setItem_id(id);

		String queryString = "from  ItemProvider where ipid=" + ipid;

		List<ItemProvider> ips = hibernateTemplate.find(queryString);

		ItemProvider tempItemProvider = null;

		if (!ips.isEmpty()) {
			tempItemProvider = ips.get(0);
		}

		ItemProvider newItemProvider = new ItemProvider();
		newItemProvider.setIpid(ipid);

		try {
			hibernateTemplate.delete(newItemProvider);
			hibernateTemplate.delete(itemtemp);
		} catch (Exception e) {
			e.printStackTrace();
			hibernateTemplate.save(tempItemProvider);

			return 0;
		}

		return 1;
	}

	public void modify(Item item) {
		hibernateTemplate.update(item);

	}

	public List<Item> getItems(final int start, final int limit) {
		List<Item> items = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Item");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Item> list = (List<Item>) query.list();
						return list;
					}
				});

		if (items != null) {
			for (int i = 0; i < items.size(); i++) {
				String tempName = (items.get(i).getCategory().getName());
				items.get(i).setCategoryName(tempName);

			}

		}

		return items;
	}

	public int getTotalNum() {
		String hql = "select count(*) from Item as item";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public Item loadById(int id) {
		String queryString = "from  Item ii where ii.item_id=" + id;

		List<Item> Items = hibernateTemplate.find(queryString);

		return Items.get(0);

	}

	public List<Item> loadByName(final String name, final int start,
			final int limit) throws Exception {
		List<Item> items = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Item item where item.item_name='"
										+ name + "'");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Item> list = (List<Item>) query.list();
						return list;
					}
				});

		return items;
	}

	public Item loadByPnum(String pnum) {
		String queryString = "from  Item ii where ii.product_num=" + pnum;

		List<Item> Items = hibernateTemplate.find(queryString);

		if(Items.size()==0)
			return null;
		
		return Items.get(0);
	}

	public List<Item> getItemsByName(final String name, final int start,
			final int limit) {
		List<Item> items = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Item item where item.item_name like'%"
										+ name + "%'");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Item> list = (List<Item>) query.list();
						return list;
					}
				});

		return items;
	}

	public List<Item> getItemsBySNum(final int store_num, final int start,
			final int limit) {
		List<Item> items = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Item item where item.store_num<='"
										+ store_num + "'");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Item> list = (List<Item>) query.list();
						return list;
					}
				});

		return items;
	}

	public int getTotalNumByName(String name) {
		String hql = "select count(*) from Item as item where item.item_name like'%"
				+ name + "%'";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public int getTotalNumBySNum(int store_num) {
		String hql = "select count(*) from Item as item where item.store_num<='"
				+ store_num + "'";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public List<Item> getItemsByCid(final int cid, final int start, final int limit) {
		List<Item> items = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Item item where cid='"
										+ cid + "'");

						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Item> list = (List<Item>) query.list();
						return list;
					}
				});

		return items;
	}


}
