package com.uf.fanfan.entity;

// Generated 2013-10-10 21:40:33 by Hibernate Tools 3.4.0.CR1

/**
 * Shopmanager generated by hbm2java
 */
public class ShopManager implements java.io.Serializable {

	private int id;
	private String name;
	private String password;

	public ShopManager() {
	}

	public ShopManager(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public ShopManager(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
