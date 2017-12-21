package com.xiuya.bean;

/**
 * 茶叶信息，目前只有鲜茶
 * @author Huangkai
 *
 */
public class Tea {

	private int id;
	private String name;
	private double price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Tea [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	
}
