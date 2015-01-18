package com.uf.rest.bean.request;

public class UpdateGoodRequest {
	private String token;
	private Integer id;
	private Integer p;
	private String name;
	private Float price;
	private Integer class_id;
	private Boolean sell;
	private Boolean is_public;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Integer getClass_id() {
		return class_id;
	}
	public void setClass_id(Integer class_id) {
		this.class_id = class_id;
	}
	public Boolean getSell() {
		return sell;
	}
	public void setSell(Boolean sell) {
		this.sell = sell;
	}
	public Boolean getIs_public() {
		return is_public;
	}
	public void setIs_public(Boolean is_public) {
		this.is_public = is_public;
	}
}
