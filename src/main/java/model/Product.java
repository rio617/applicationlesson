package model;

import java.io.Serializable;

public class Product implements Serializable {
	String name;
	String price;
	String updated;
	
	public Product() {
		
	}

	public Product(String name, String price, String updated) {
		super();
		this.name = name;
		this.price = price;
		this.updated = updated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}
	
	

}
