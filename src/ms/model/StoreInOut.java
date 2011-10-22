package ms.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class StoreInOut { // 库存变动单

	private int inOutId; // 单据ID
	private String code; // 单据编号
	private Date billDate; // 单据日期
	private String operator; // 操作员
	private Store store; // 仓库ID
	private int satid; // 进/出标志
	private String people; // 取料人
	private double totalM; // 总金额
	private String memo; // 备注
	private Date updateTime; // 修改日期
	private int RPTID; // 打印样式

	@Id
	@GeneratedValue
	public int getInOutId() {
		return inOutId;
	}

	public void setInOutId(int inOutId) {
		this.inOutId = inOutId;
	}

	@Column(columnDefinition = "varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	@Column(columnDefinition ="varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name = "storeid")
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public int getSatid() {
		return satid;
	}

	public void setSatid(int satid) {
		this.satid = satid;
	}
	@Column(columnDefinition ="varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public double getTotalM() {
		return totalM;
	}

	public void setTotalM(double totalM) {
		this.totalM = totalM;
	}
	@Column(columnDefinition ="varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getRPTID() {
		return RPTID;
	}

	public void setRPTID(int rPTID) {
		RPTID = rPTID;
	}

}
