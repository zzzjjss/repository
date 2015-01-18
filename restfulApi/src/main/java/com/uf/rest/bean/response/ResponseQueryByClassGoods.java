package com.uf.rest.bean.response;

import java.util.List;

public class ResponseQueryByClassGoods {
	private Integer id;
	private String name;
	private Boolean is_public;
	private String time;
	private List<ResponseQueryByClassGood> good;
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
	public Boolean getIs_public() {
		return is_public;
	}
	public void setIs_public(Boolean is_public) {
		this.is_public = is_public;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public List<ResponseQueryByClassGood> getGood() {
		return good;
	}
	public void setGood(List<ResponseQueryByClassGood> good) {
		this.good = good;
	}
	
}
