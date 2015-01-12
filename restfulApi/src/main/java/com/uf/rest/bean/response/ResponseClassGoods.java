package com.uf.rest.bean.response;

import java.util.List;

public class ResponseClassGoods {
	private String goodClass;
	private List<ResponseGood> goods;
	public String getGoodClass() {
		return goodClass;
	}
	public void setGoodClass(String goodClass) {
		this.goodClass = goodClass;
	}
	public List<ResponseGood> getGoods() {
		return goods;
	}
	public void setGoods(List<ResponseGood> goods) {
		this.goods = goods;
	}
	
	
}
