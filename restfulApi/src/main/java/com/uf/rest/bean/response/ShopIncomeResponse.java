package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class ShopIncomeResponse {
	private boolean success;
	private ResponseError error;
	private ShopIncomeResponseData data;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ResponseError getError() {
		return error;
	}
	public void setError(ResponseError error) {
		this.error = error;
	}
	public ShopIncomeResponseData getData() {
		return data;
	}
	public void setData(ShopIncomeResponseData data) {
		this.data = data;
	}
	
	
}
