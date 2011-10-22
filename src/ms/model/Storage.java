package ms.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Storage {
	private int sid;
	private int store_num;	//库存数量
	private Item item;

	@Id
	public int getSid() {
		return sid;
	}

	public void setSid(int item_id) {
		this.sid = item_id;
	}

	public int getStore_num() {
		return store_num;
	}

	public void setStore_num(int store_num) {
		this.store_num = store_num;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "item_id")
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	
}
