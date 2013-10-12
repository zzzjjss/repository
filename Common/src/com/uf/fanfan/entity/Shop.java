package com.uf.fanfan.entity;

// Generated 2013-10-10 21:40:33 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Shop generated by hbm2java
 */
public class Shop implements java.io.Serializable {

	private int id;
	private String name;
	private String address;
	private String phoneNum;
	private String description;
	private Set<Product> products = new HashSet<Product>(0);

	public Shop() {
	}

	public Shop(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Shop(int id, String name, String address, String phoneNum,
			String description, Set<Product> products) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.description = description;
		this.products = products;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
