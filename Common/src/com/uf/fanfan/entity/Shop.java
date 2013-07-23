package com.uf.fanfan.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the shop database table.
 * 
 */
@Entity
@TableGenerator(name = "Shop_ID_GEN", table = "ID_TABLE", pkColumnName = "ID_KEY", valueColumnName = "ID_VALUE", pkColumnValue = "Shop_ID",allocationSize=1)
public class Shop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="Shop_ID_GEN")
	private Integer id;

	private String address;

	private String description;

	private String name;

	private String phoneNum;

	
	@OneToMany(mappedBy="shop")
	private List<Product> products;

	public Shop() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProduct(List<Product> products) {
		this.products = products;
	}

}