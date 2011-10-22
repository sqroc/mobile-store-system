package ms.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class ItemProvider {
	private int ipid;
	private Provider provider;   //生产企业
	private Item item;
	private double cost;	//进价
	private double price;	//售价		

	private int pid;
	private int itid;
	private String pName;
	private String item_name;

	@Id
	@GeneratedValue
	public int getIpid() {
		return ipid;
	}

	public void setIpid(int ipid) {
		this.ipid = ipid;
	}

	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pid")
	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "item_id")
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	@Transient
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}
	@Transient
	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	@Transient
	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}
	@Transient
	public int getItid() {
		return itid;
	}

	public void setItid(int itid) {
		this.itid = itid;
	}

}
