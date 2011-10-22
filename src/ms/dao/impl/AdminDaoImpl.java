package ms.dao.impl;

import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import ms.dao.AdminDao;
import ms.model.Admin;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository("AdminDao")
public class AdminDaoImpl implements AdminDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(Admin admin) {
		hibernateTemplate.save(admin);

	}

	public int deleletByid(int id) {
		Admin Atemp = new Admin();
		Atemp.setAid(id);
		try {
			hibernateTemplate.delete(Atemp);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}

	public void modify(Admin admin) {
		hibernateTemplate.update(admin);

	}

	public Admin check(Admin admin) {
		String queryString = "from Admin ad where ad.username='"
				+ admin.getUsername() + "' and ad.password='"
				+ admin.getPassword() + "'";

		List<Admin> admins = hibernateTemplate.find(queryString);
		
		if (admins != null && admins.size() > 0){
			
			
			return admins.get(0);
		}
		else
		{
			return null;
		}
	}

	public Admin loadByAid(int aid) {
		return (Admin) this.hibernateTemplate.load(Admin.class, aid);
	}

	public List<Admin> getAdmins(final int start, final int limit) {
		List<Admin> admins = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from Admin");
						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<Admin> list = (List<Admin>) query.list();
						return list;
					}
				});

		return admins;
	}

	public int getTotalNum() {
		String hql = "select count(*) from Admin as admin";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public List<Admin> getOnlineAdmins(Admin admin) {
		String sql = "from Admin as ad where ad.state = 1 and ad.name <> '"
				+ admin.getUsername() + "'";
		List<Admin> admins = hibernateTemplate.find(sql);

		return admins;
	}

	public List<Admin> loadByName(String name) {
		List<Admin> admins = null;
		String sql = "from Admin as ad where ad.username = '"
				+ name + "'";
		admins = hibernateTemplate.find(sql);
		return admins;
	}

}
