package ms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ms.dao.ProviderDao;
import ms.model.Admin;
import ms.model.Category;
import ms.model.Provider;

@Repository("ProviderDao")
public class ProviderDaoImpl implements ProviderDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(Provider provider) {
		hibernateTemplate.save(provider);

	}

	public int deleletByid(int id) {
		Provider provider = new Provider();
		provider.setPid(id);
		try {
			hibernateTemplate.delete(provider);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}

	public void modify(Provider provider) {
		hibernateTemplate.update(provider);

	}

	public Provider loadByAid(int pid) {
		
		String queryString = "from  Provider pro where pro.pid=" + pid;

		List<Provider> Providers = hibernateTemplate.find(queryString);

		return Providers.get(0);
		
	}

	public List<Provider> getProviders(final int start, final int limit) {
		List<Provider> providers = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Provider");
						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Provider> list = (List<Provider>) query.list();
						return list;
					}
				});

		return providers;
	}

	public int getTotalNum() {
		String hql = "select count(*) from Provider as provider";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public List<String> getPNames()
	{
		String queryString = "select name from Provider as provider";
		List<String> PNames = hibernateTemplate.find(queryString);
		
	
		return PNames ;
		
	}
	
	public Provider loadByName(String pname) 
	{
		String queryString = "from  Provider pro where pro.name='" + pname+"'";

		List<Provider> Providers = hibernateTemplate.find(queryString);

		return Providers.get(0);
	}

	public List<Provider> loadByNames(String pname) {
		String queryString = "from  Provider pro where pro.name='" + pname+"'";

		List<Provider> Providers = hibernateTemplate.find(queryString);

		return Providers;
	}

	public List<Provider> getProviders(final String name, final int start, final int limit) {
		List<Provider> providers = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Provider pro where pro.name like'%" + name+"%'");
						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Provider> list = (List<Provider>) query.list();
						return list;
					}
				});

		return providers;
	}

	public int getTotalNumByName(String name) {
		String hql = "select count(*) from Provider as provider where provider.name like'%" + name+"%'";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}
}
