package com.uf.rest.bean.request;

public class ShopGradeRequest {
	private String token;
	private Integer p;
	private Integer  id;
	private Float score;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	
	
}
