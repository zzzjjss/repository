package com.uf.rest.bean.response;


public class ResponseShop {
	private Integer id;
	private String name;
	private byte[] icon;
	private String time;
	private ResponseLocation  location;
	private String mark;
	private ResponseCoordinate coordinate;
	private String[] phone;
	private String type;
	
	
	public ResponseCoordinate getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(ResponseCoordinate coordinate) {
		this.coordinate = coordinate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public ResponseLocation getLocation() {
		return location;
	}
	public void setLocation(ResponseLocation location) {
		this.location = location;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String[] getPhone() {
		return phone;
	}
	public void setPhone(String[] phone) {
		this.phone = phone;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
