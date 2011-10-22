package ms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	private int uid;
	private String username;
	private String password;
	private String realname;
	private String address;
	private String phone;
	private String postcode;
	private double money;	//会员金额

	@Id
	@GeneratedValue
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	@Column(columnDefinition ="varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Column(columnDefinition ="varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(columnDefinition ="varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
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
	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

}
