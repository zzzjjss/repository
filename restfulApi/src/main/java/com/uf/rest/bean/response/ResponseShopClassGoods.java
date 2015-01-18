package com.uf.rest.bean.response;

import java.util.List;

public class ResponseShopClassGoods {
	private Integer class_id;
	private String class_name;
	private List<ResponseGood> good;
	public Integer getClass_id() {
		return class_id;
	}
	public void setClass_id(Integer class_id) {
		this.class_id = class_id;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public List<ResponseGood> getGood() {
		return good;
	}
	public void setGood(List<ResponseGood> good) {
		this.good = good;
	}
	
	
}
