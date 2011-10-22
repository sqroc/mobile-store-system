package ms.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ms.dao.UserDao;
import ms.model.Admin;
import ms.model.Category;
import ms.model.User;

@Repository("UserDao")
public class UserDaoImpl implements UserDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(User user) {
		hibernateTemplate.save(user);

	}

	public int deleletByid(int id) {
		User utemp = new User();
		utemp.setUid(id);
		try {
			hibernateTemplate.delete(utemp);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}

	public void modify(User user) {
		hibernateTemplate.update(user);

	}

	public User check(User user) {
		String queryString = "from User user where user.username='"
				+ user.getUsername() + "' and user.password='"
				+ user.getPassword() + "'";

		List<User> users = hibernateTemplate.find(queryString);
		
		if (users != null && users.size() > 0){
			
			
			return users.get(0);
		}
		else
		{
			return null;
		}
	}

	public User loadByUid(int uid) {
		//return (User) this.hibernateTemplate.load(User.class, uid);
		
		
		
		
		String queryString = "from  User user where user.uid=" + uid;

		List<User> Users = hibernateTemplate.find(queryString);

		return Users.get(0);
	}

	public List<User> getUsers(final int start, final int limit) {
		List<User> users = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from User");
						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<User> list = (List<User>) query.list();
						return list;
					}
				});

		return users;
	}

	public int getTotalNum() {
		String hql = "select count(*) from User as user";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

	public User loadByName(String name) throws Exception {
		String queryString = "from  User user where user.username='" + name
				+ "'";

		List<User> users = hibernateTemplate.find(queryString);

		return users.get(0);
	}

	public List<User> loadByNames(String name) throws Exception {
		String queryString = "from  User user where user.username='" + name
				+ "'";

		List<User> users = hibernateTemplate.find(queryString);

		return users;
	}

	public List<User> getUsersByName(final String name, final int start, final int limit) {
		List<User> users = hibernateTemplate
				.executeFind(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						org.hibernate.Query query = (org.hibernate.Query) session
								.createQuery("from User user where user.username like'%" + name
				+ "%'");
						query.setFirstResult(start);
						query.setMaxResults(limit);

						List<User> list = (List<User>) query.list();
						return list;
					}
				});

		return users;

	}

	public int getTotalNumByName(String name) {
		String hql = "select count(*) from User as user where user.username like'%" + name
				+ "%'";
		return ((Long) getHibernateTemplate().iterate(hql).next()).intValue();
	}

}
