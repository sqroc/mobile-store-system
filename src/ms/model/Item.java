package ms.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Item {
	private int item_id;
	private String item_name; // 中文名称
	private String e_item_name; // 英文名称
	private Category category; // 作用类别
	private String standard; // 规格
	private String product_num; // 产品编号(批准文号)
	private String img_url;
	private String intro; // 功效主治
	private int store_num;  //库存
	private double cost;	//进价
	private double price;	//售价	
	private Store store;

	private String categoryName;// 分类名，不参与持久化 为解决内部类难题 暂为

	private String providerName;
	
	private int ipid;
	@Id
	@GeneratedValue
	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	@Column(columnDefinition = "varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "cid")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(columnDefinition = "varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	@Column(columnDefinition = "varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getProduct_num() {
		return product_num;
	}

	public void setProduct_num(String product_num) {
		this.product_num = product_num;
	}

	@Column(columnDefinition = "varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	@Transient
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(columnDefinition = "varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getE_item_name() {
		return e_item_name;
	}

	public void setE_item_name(String e_item_name) {
		this.e_item_name = e_item_name;
	}

	@Column(columnDefinition = "varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getIntro() {
		return intro;
	}

	
	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getStore_num() {
		return store_num;
	}

	public void setStore_num(int store_num) {
		this.store_num = store_num;
	}
	@Transient
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	@Transient
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	@Transient
	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	@Transient
	public int getIpid() {
		return ipid;
	}

	public void setIpid(int ipid) {
		this.ipid = ipid;
	}
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.PERSIST})
	@JoinColumn(name = "storeid")
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}


	
	
}
