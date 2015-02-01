package com.uf.rest.bean.push;


public class ChangeOrderStatePushContent {
	private Integer id;
	private String state;
	private PushShop shop;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public PushShop getShop() {
		return shop;
	}
	public void setShop(PushShop shop) {
		this.shop = shop;
	}
	
	
}
