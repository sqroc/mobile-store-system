package ms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

import ms.dao.UserDao;
import ms.model.Admin;
import ms.model.User;
import ms.service.UserManager;

@Service("userManager")
@RemotingDestination(channels = { "my-amf" })
public class UserManagerImpl implements UserManager {
	private UserDao userdao;

	public UserDao getUserdao() {
		return userdao;
	}

	@Resource
	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}

	@RemotingInclude
	public boolean check(String username, String password) throws Exception {
		User tempUser = new User();

		tempUser.setUsername(username);
		tempUser.setPassword(password);

		User newUser = userdao.check(tempUser);

		if (newUser == null) {
			return false;
		} else {
			return true;
		}
	}

	@RemotingInclude
	public String add(User user) throws Exception {
		List<User> users = null;
		users = userdao.loadByNames(user.getUsername());
		if (users.size() != 0) {
			return "failed";
		} else {
			userdao.save(user);
			return "succeed";
		}

	}

	@RemotingInclude
	public int deleletByid(int uid) throws Exception {

		return userdao.deleletByid(uid);
	}

	@RemotingInclude
	public String modify(User user) throws Exception {
		userdao.modify(user);
		return "succeed";

	}

	@RemotingInclude
	public List<User> getUsers(String paramter,int start, int limit,int type) throws Exception {

		if(type==1){
			return userdao.getUsersByName(paramter, start, limit);
		}else{
			return userdao.getUsers(start, limit);
		}
		
	}

	@RemotingInclude
	public User loadById(int uid) throws Exception {

		return userdao.loadByUid(uid);
	}

	@RemotingInclude
	public int getTotalNum(String paramter,int type) throws Exception {

		if(type==1){
			return userdao.getTotalNumByName(paramter);
		}else{
			return userdao.getTotalNum();
		}
		
	}

	@RemotingInclude
	public List<User> getOnlineUsers(User user) throws Exception {

		return null;
	}

	public User loadByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return userdao.loadByName(name);
	}

}
