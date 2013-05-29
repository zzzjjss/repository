package com.uf.fanfan.entity;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.*;


/**
 * The persistent class for the agent database table.
 * 
 */
@Entity
@TableGenerator(name = "Agent_ID_GEN", table = "ID_TABLE", pkColumnName = "ID_KEY", valueColumnName = "ID_VALUE", pkColumnValue = "agent_ID",allocationSize=1)
public class Agent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="Agent_ID_GEN")
	private int id;

	private String address;

	private float backMoney;

	private float balance;

	private String name;

	private String password;

	private String phoneNum;

	private String realName;

	public Agent() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getBackMoney() {
		return this.backMoney;
	}

	public void setBackMoney(float backMoney) {
		this.backMoney = backMoney;
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

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

}