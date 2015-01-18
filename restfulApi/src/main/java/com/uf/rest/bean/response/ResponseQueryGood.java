package com.uf.rest.bean.response;

public class ResponseQueryGood {
	private Integer id;
	private String name;
	private Float price;
	private String time;
	private Boolean is_public;
	private Boolean sell;
	private ResponseGoodClass goodClass;
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
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Boolean getIs_public() {
		return is_public;
	}
	public void setIs_public(Boolean is_public) {
		this.is_public = is_public;
	}
	public Boolean getSell() {
		return sell;
	}
	public void setSell(Boolean sell) {
		this.sell = sell;
	}
	public ResponseGoodClass getGoodClass() {
		return goodClass;
	}
	public void setGoodClass(ResponseGoodClass goodClass) {
		this.goodClass = goodClass;
	}
	
}
