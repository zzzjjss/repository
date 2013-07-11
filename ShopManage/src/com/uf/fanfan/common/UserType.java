package com.uf.fanfan.common;

public enum UserType {
	CUSTOMER("customer"),AGENT("agent"),SHOP_Manager("shopManager"),PLATFORM_ADMIN("platformAdmin"),DELIVERY_MAN("deliveryMan");
	private String name;
	private UserType(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
}
