package com.uf.rest.bean.request;

public class UserLogoutRequest {
	private String token;
	private String p;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	
}
