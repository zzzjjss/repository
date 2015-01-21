package com.uf.rest.entity;

import java.util.Date;

public class ShopWithDrawRecord {
	private Integer id;
	private Date withdrawTime;
	private Float money;
	private Shop shop;
	private Integer state;
	
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getWithdrawTime() {
		return withdrawTime;
	}
	public void setWithdrawTime(Date withdrawTime) {
		this.withdrawTime = withdrawTime;
	}
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	
}
