package com.uf.broadcast.entity;

public class Organization {
	private Long id;
	private float locationX;
	private float locationY;
	private int pointAmount;
	private String address;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public float getLocationX() {
		return locationX;
	}
	public void setLocationX(float locationX) {
		this.locationX = locationX;
	}
	public float getLocationY() {
		return locationY;
	}
	public void setLocationY(float locationY) {
		this.locationY = locationY;
	}
	public int getPointAmount() {
		return pointAmount;
	}
	public void setPointAmount(int pointAmount) {
		this.pointAmount = pointAmount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
