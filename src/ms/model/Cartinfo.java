package ms.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cartinfo {

	private int ciid;
	private int uid;
	private Item item;
	private int number;
	private double allprice;
	private int isorder;
	
	@Id
	@GeneratedValue
	public int getCiid() {
		return ciid;
	}

	public void setCiid(int ciid) {
		this.ciid = ciid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
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

	public double getAllprice() {
		return allprice;
	}

	public void setAllprice(double allprice) {
		this.allprice = allprice;
	}


	public int getIsorder() {
		return isorder;
	}

	public void setIsorder(int isorder) {
		this.isorder = isorder;
	}

	
}
