package com.uf.rest.entity;

public class User implements java.io.Serializable{
	private Integer id;
	private String name;
	private String password;
	private int registType;
	private int platform;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRegistType() {
		return registType;
	}
	public void setRegistType(int registType) {
		this.registType = registType;
	}
	public int getPlatform() {
		return platform;
	}
	public void setPlatform(int platform) {
		this.platform = platform;
	}
	
}
