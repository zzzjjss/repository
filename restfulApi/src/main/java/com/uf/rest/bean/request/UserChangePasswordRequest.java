package com.uf.rest.bean.request;

public class UserChangePasswordRequest {
	private String token;
	private String old_psd;
	private String new_psd;
	private Integer p;
	
	public Integer getP() {
		return p;
	}
	public void setP(Integer p) {
		this.p = p;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getOld_psd() {
		return old_psd;
	}
	public void setOld_psd(String old_psd) {
		this.old_psd = old_psd;
	}
	public String getNew_psd() {
		return new_psd;
	}
	public void setNew_psd(String new_psd) {
		this.new_psd = new_psd;
	}
	
}
