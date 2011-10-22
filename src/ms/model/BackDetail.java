package ms.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BackDetail {

	private int backDid; // 退货明细ID
	private BackDraw backDraw; // 主表ID
	private Item item; // 货品ID
	private int quantity; // 数量
	private double price; // 该商品单价
	private String code;

	@Id
	@GeneratedValue
	public int getBackDid() {
		return backDid;
	}

	public void setBackDid(int backDid) {
		this.backDid = backDid;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(name = "backDrawId")
	public BackDraw getBackDraw() {
		return backDraw;
	}

	public void setBackDraw(BackDraw backDraw) {
		this.backDraw = backDraw;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST })
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
