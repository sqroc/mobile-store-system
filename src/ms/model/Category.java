package ms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category {
	private int cid;
	private String name;

	@Id
	@GeneratedValue
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}
	@Column(columnDefinition ="varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
