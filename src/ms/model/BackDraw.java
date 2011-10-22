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
public class BackDraw { // 退货单

	private int backDrawId; // 单据ID
	private String code; // 单据号（自动注入）
	private Date billDate; // 单据日期
	private String operator; // 操作员
	private String backMan; // 退货员
	private Store store; // 退入仓库号
	private double totalM; // 总金额
	private String memo; // 备注
	private Date updateTime; // 用户生成退货日期（自动注入）
	private int PTRID; // 打印样式
	private String state; // 状态
	private User user; // 用户

	@Id
	@GeneratedValue
	public int getBackDrawId() {
		return backDrawId;
	}

	public void setBackDrawId(int backDrawId) {
		this.backDrawId = backDrawId;
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

	@Column(columnDefinition = "varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(columnDefinition = "varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getBackMan() {
		return backMan;
	}

	public void setBackMan(String backMan) {
		this.backMan = backMan;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name = "storeid")
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public double getTotalM() {
		return totalM;
	}

	public void setTotalM(double totalM) {
		this.totalM = totalM;
	}

	@Column(columnDefinition = "varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci")
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

	public int getPTRID() {
		return PTRID;
	}

	public void setPTRID(int pTRID) {
		PTRID = pTRID;
	}

	@Column(columnDefinition = "varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name = "uid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
