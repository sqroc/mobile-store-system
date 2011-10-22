package ms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import ms.dao.AdminDao;
import ms.dao.impl.AdminDaoImpl;
import ms.model.Admin;
import ms.service.AdminManager;

import org.apache.bcel.generic.NEW;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingExclude;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

@Service("adminManager")
@RemotingDestination(channels={"my-amf"})
public class AdminManagerImpl implements AdminManager {
	private AdminDao admindao ;

	public AdminDao getAdmindao() {
		return admindao;
	}

	@Resource
	public void setAdmindao(AdminDao admindao) {
		this.admindao = admindao;
	}

	@RemotingInclude
	public Admin check(String username,String password) throws Exception {
		Admin tempAdmin = new Admin();

		tempAdmin.setUsername(username);
		tempAdmin.setPassword(password);
	

        Admin newAdmin = admindao.check(tempAdmin);
		
       return newAdmin;
	}
	
	
	
	@RemotingInclude
	public String add(Admin admin) throws Exception {
		List<Admin> admins = null;
		admins = admindao.loadByName(admin.getUsername());
		if(admins.size()!=0){
			return "failed";
		}else{
			admindao.save(admin);
			return "succeed";
		}
	}
	@RemotingInclude
	public int deleletByid(int aid) throws Exception {
		return admindao.deleletByid(aid);
		
		
	}
	@RemotingInclude
	public String modify(Admin admin) throws Exception {
			admindao.modify(admin);
			return "succeed";
		
	}

	@RemotingInclude
	public List<Admin> getAdmins(int start, int limit) throws Exception {
		return admindao.getAdmins(start, limit);
	}
	@RemotingInclude
	public Admin loadById(int aid) throws Exception {
		return admindao.loadByAid(aid);
	}
	@RemotingInclude
	public int getTotalNum() throws Exception {
		return admindao.getTotalNum();
	}
	@RemotingInclude
	public List<Admin> getOnlineAdmins(Admin admin) throws Exception {
		return admindao.getOnlineAdmins(admin);
	}

}
