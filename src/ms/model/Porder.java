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
public class Porder {
	private int porid; // 采购订单主表ID
	private String code; // 单据编号
	private Date billDate; // 单据日期
	private String operator; // 操作员
	private String exeman; // 经办人
	private Provider provider; // 供应商
	private double amount; // 金额
	private PayType paytype; // 付款方式
	private Date revDate; // 交货日期
	private String billto; // 交货地址
	private String memo; // 备注
	private Date updateTime; // 修改日期
	private int rptid; // 打印样式

	@Id
	@GeneratedValue
	public int getPorid() {
		return porid;
	}

	public void setPorid(int porid) {
		this.porid = porid;
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
	@Column(columnDefinition ="varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getExeman() {
		return exeman;
	}

	public void setExeman(String exeman) {
		this.exeman = exeman;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name = "pid")
	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name = "ptid")
	public PayType getPaytype() {
		return paytype;
	}

	public void setPaytype(PayType paytype) {
		this.paytype = paytype;
	}

	public Date getRevDate() {
		return revDate;
	}

	public void setRevDate(Date revDate) {
		this.revDate = revDate;
	}
	@Column(columnDefinition = "varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getBillto() {
		return billto;
	}

	public void setBillto(String billto) {
		this.billto = billto;
	}
	@Column(columnDefinition = "varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci")
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

	public int getRptid() {
		return rptid;
	}

	public void setRptid(int rptid) {
		this.rptid = rptid;
	}

}
