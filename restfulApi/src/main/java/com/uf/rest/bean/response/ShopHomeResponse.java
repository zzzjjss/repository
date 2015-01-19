package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class ShopHomeResponse {
	private boolean success;
	private ResponseError error;
	private ShopHomeResponseData data;
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
	public ShopHomeResponseData getData() {
		return data;
	}
	public void setData(ShopHomeResponseData data) {
		this.data = data;
	}
	
	

}
