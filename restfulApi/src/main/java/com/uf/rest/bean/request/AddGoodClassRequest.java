package com.uf.rest.bean.request;

public class AddGoodClassRequest {
	private String token;
	private Integer p;
	private String name;
	private Boolean is_public ;
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
	public Boolean getIs_public() {
		return is_public;
	}
	public void setIs_public(Boolean is_public) {
		this.is_public = is_public;
	}
	
	
}
