package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class AddGoodClassResponse {
	private boolean success;
	private ResponseError error;
	private AddGoodClassResponseData data;
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
	public AddGoodClassResponseData getData() {
		return data;
	}
	public void setData(AddGoodClassResponseData data) {
		this.data = data;
	}
	
}
