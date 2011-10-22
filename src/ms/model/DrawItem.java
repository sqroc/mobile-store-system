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
public class DrawItem { // 领货单（主表）
	private int drawItemId; // 单据ID
	private String code; // 单据编号
	private Date billDate; // 单据日期
	private String operator; // 操作员姓名
	private String drawMan; // 领料人姓名
	private Store store; // 仓库ID
	private String memo; // 备注
	private Date updatetime; // 修改日期
	private int rptid; // 打印样式ID

	@Id
	@GeneratedValue
	public int getDrawItemId() {
		return drawItemId;
	}

	public void setDrawItemId(int drawItemId) {
		this.drawItemId = drawItemId;
	}
	@Column(columnDefinition ="varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci")
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
	public String getDrawMan() {
		return drawMan;
	}

	public void setDrawMan(String drawMan) {
		this.drawMan = drawMan;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name = "storeid")
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	@Column(columnDefinition ="varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public int getRptid() {
		return rptid;
	}

	public void setRptid(int rptid) {
		this.rptid = rptid;
	}

}
