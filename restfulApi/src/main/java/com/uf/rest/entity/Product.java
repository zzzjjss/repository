package com.uf.rest.entity;

import java.util.Date;

public class Product {
	private Integer id;
	private String name;
	private Float defaultPrice;
	private ProductClass productClass;
	private Date addTime;
	
	
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Float getDefaultPrice() {
		return defaultPrice;
	}
	public void setDefaultPrice(Float defaultPrice) {
		this.defaultPrice = defaultPrice;
	}
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
	
}
