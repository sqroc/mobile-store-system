package ms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Provider {
	private int pid;
	private String name;
	private String postcode;
	private String address;
	private String phone;
	private String supervisor;   //负责人

	@Id
	@GeneratedValue
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
	@Column(columnDefinition ="varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(columnDefinition ="varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	@Column(columnDefinition ="varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(columnDefinition ="varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(columnDefinition ="varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

}
