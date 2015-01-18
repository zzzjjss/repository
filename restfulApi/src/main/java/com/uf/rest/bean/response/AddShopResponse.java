package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class AddShopResponse {
	private boolean success;
	private ResponseError error;
	private AddShopResponseData  data;
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
	public AddShopResponseData getData() {
		return data;
	}
	public void setData(AddShopResponseData data) {
		this.data = data;
	}
	
}
