package ms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Admin {
	private int aid;
	private String password;
	private String username;
	private int gender;
	private int rank;		//管理员权限
	private Date addDate;

	@Id
	@GeneratedValue
	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}
	@Column(columnDefinition ="varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(columnDefinition ="varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Column(columnDefinition ="varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

}
