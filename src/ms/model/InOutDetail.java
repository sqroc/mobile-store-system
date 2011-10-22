package ms.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class InOutDetail {
	private int IODid; // 库存变动明细ID
	private StoreInOut inOut; // 库存变动单据ID
	private Item item; // 货品ID
	private double price; // 单价
	private int quantity; // 数量
	private int backQTY; // 退货数量
	private Date date;         //变动时间
	private Category category;   //药品分类
	private int satid; // 进/出标志

	@Id
	@GeneratedValue
	public int getIODid() {
		return IODid;
	}

	public void setIODid(int iODid) {
		IODid = iODid;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name = "inOutId")
	public StoreInOut getInOut() {
		return inOut;
	}

	public void setInOut(StoreInOut inOut) {
		this.inOut = inOut;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name = "item_id")
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getBackQTY() {
		return backQTY;
	}

	public void setBackQTY(int backQTY) {
		this.backQTY = backQTY;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "cid")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getSatid() {
		return satid;
	}

	public void setSatid(int satid) {
		this.satid = satid;
	}

	
	
}
