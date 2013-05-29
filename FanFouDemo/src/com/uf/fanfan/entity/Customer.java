package com.uf.fanfan.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@TableGenerator(name = "Customer_ID_GEN", table = "ID_TABLE", pkColumnName = "ID_KEY", valueColumnName = "ID_VALUE", pkColumnValue = "customer_ID",allocationSize=1)
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="Customer_ID_GEN")
	private int id;

	private int agentid;

	private float balance;

	private String name;

	private String password;

	private float totalConsume;

	public Customer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAgentid() {
		return this.agentid;
	}

	public void setAgentid(int agentid) {
		this.agentid = agentid;
	}

	public float getBalance() {
		return this.balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
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

	public float getTotalConsume() {
		return this.totalConsume;
	}

	public void setTotalConsume(float totalConsume) {
		this.totalConsume = totalConsume;
	}

}