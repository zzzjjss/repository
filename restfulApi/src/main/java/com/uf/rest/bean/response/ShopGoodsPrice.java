package com.uf.rest.bean.response;

import java.util.List;

public class ShopGoodsPrice {
	private ResponseShop shop;
	private List<ResponseGood> good;
	public ResponseShop getShop() {
		return shop;
	}
	public void setShop(ResponseShop shop) {
		this.shop = shop;
	}
	public List<ResponseGood> getGood() {
		return good;
	}
	public void setGood(List<ResponseGood> good) {
		this.good = good;
	}
	
}
