package com.uf.rest.bean.response;

import java.util.List;

public class QueryShopInfoResponseData {
	private String name;
	private byte[] icon;
	private String mark;
	private String time;
	private String[] phone;
	private ResponseLocation  location;
	private ResponseCoordinate coordinate;
	private List<ResponseShopClassGoods> goods;
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
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String[] getPhone() {
		return phone;
	}
	public void setPhone(String[] phone) {
		this.phone = phone;
	}
	public ResponseLocation getLocation() {
		return location;
	}
	public void setLocation(ResponseLocation location) {
		this.location = location;
	}
	public ResponseCoordinate getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(ResponseCoordinate coordinate) {
		this.coordinate = coordinate;
	}
	public List<ResponseShopClassGoods> getGoods() {
		return goods;
	}
	public void setGoods(List<ResponseShopClassGoods> goods) {
		this.goods = goods;
	}
	
}
