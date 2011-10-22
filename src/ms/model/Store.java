package ms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Store {

	private int storeid;   //仓库ID
	private String name;   //仓库名称
	private String location;   //地址
	private String memo;     //备注
	private String upflag;    //发送标志

	@Id
	@GeneratedValue
	public int getStoreid() {
		return storeid;
	}

	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}
	@Column(columnDefinition ="varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(columnDefinition ="varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	@Column(columnDefinition ="varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Column(columnDefinition ="varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getUpflag() {
		return upflag;
	}

	public void setUpflag(String upflag) {
		this.upflag = upflag;
	}

}
