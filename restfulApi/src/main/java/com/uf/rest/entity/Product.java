package com.uf.rest.entity;

public class Product {
	private Integer id;
	private String name;
	private Float price;
	private Shop shop;
	private ProductClass productClass;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProductClass getProductClass() {
		return productClass;
	}
	public void setProductClass(ProductClass productClass) {
		this.productClass = productClass;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
	
}
