package com.uf.rest.bean.request;

import java.util.List;

public class UpdateShopRequest {
	private String token;
	private Integer p;
	private String name;
	private byte[] icon;
	private String[] phone;
	private ShopLocation location;
	private ShopCoordinate coordinate;
	private GoodInfo[] good;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getP() {
		return p;
	}
	public void setP(Integer p) {
		this.p = p;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getIcon() {
		return icon;
	}
	public void setIcon(byte[] icon) {
		this.icon = icon;
	}
	public String[] getPhone() {
		return phone;
	}
	public void setPhone(String[] phone) {
		this.phone = phone;
	}
	public ShopLocation getLocation() {
		return location;
	}
	public void setLocation(ShopLocation location) {
		this.location = location;
	}
	public ShopCoordinate getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(ShopCoordinate coordinate) {
		this.coordinate = coordinate;
	}
	public GoodInfo[] getGood() {
		return good;
	}
	public void setGood(GoodInfo[] good) {
		this.good = good;
	}
	
	
}
