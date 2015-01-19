package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class ShopOrderResponse {
	private boolean success;
	private ResponseError error;
	private ShopOrderResponseData data;
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
	public ShopOrderResponseData getData() {
		return data;
	}
	public void setData(ShopOrderResponseData data) {
		this.data = data;
	}
	
}
