package ms.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PorderDetail {

	private int pordid; // 采购订单从表 ID
	private Porder porder; // 主表ID
	private Item item; // 商品ID
	private int quantity; // 数量
	private double price; // 单价
	private Store store; // 进入仓库ID
	private String code; 

	@Id
	@GeneratedValue
	public int getPordid() {
		return pordid;
	}

	public void setPordid(int pordid) {
		this.pordid = pordid;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name = "pid")
	public Porder getPorder() {
		return porder;
	}

	public void setPorder(Porder porder) {
		this.porder = porder;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name = "item_id")
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name = "storeid")
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
