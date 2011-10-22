package ms.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class OrderItem {
	
	private int oid;     //订单号
	private Item item;   //购买的商品
	private int number; // 数量
	private double price; // 单个商品价格
	private Cart cart;   //对应的总订单号

	
	private int itemId;//物品号
	private int cartId;//订单号
	
	
	@Id
	@GeneratedValue
	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name = "item_id")
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name = "cart_id")
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	
	@Transient
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	@Transient
	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	
	
	
	
	
	
	
}
