package com.uf.fanfan.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the shopmanager database table.
 * 
 */
@Entity
@TableGenerator(name = "Shopmanager_ID_GEN", table = "ID_TABLE", pkColumnName = "ID_KEY", valueColumnName = "ID_VALUE", pkColumnValue = "shopmanager_ID",allocationSize=1)
public class ShopManager implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="Shopmanager_ID_GEN")
	private int id;

	private String name;

	private String password;

	public ShopManager() {
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