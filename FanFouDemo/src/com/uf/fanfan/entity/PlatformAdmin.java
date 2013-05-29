package com.uf.fanfan.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the platformadmin database table.
 * 
 */
@Entity
@TableGenerator(name = "Platformadmin_ID_GEN", table = "ID_TABLE", pkColumnName = "ID_KEY", valueColumnName = "ID_VALUE", pkColumnValue = "platformadmin_ID",allocationSize=1)
public class PlatformAdmin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="Platformadmin_ID_GEN")
	private int id;

	private String name;

	private String password;

	public PlatformAdmin() {
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