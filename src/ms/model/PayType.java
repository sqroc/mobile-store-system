package ms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PayType { // 付款方式表

	private int ptid; // ID
	private String name; // 付款名称
	private String bankId; // 帐号ID
	private String detail; // 付款说明
	private String memo; // 备注

	@Id
	@GeneratedValue
	public int getPtid() {
		return ptid;
	}

	public void setPtid(int ptid) {
		this.ptid = ptid;
	}
	@Column(columnDefinition ="varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(columnDefinition ="varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	@Column(columnDefinition ="varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	@Column(columnDefinition ="varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
