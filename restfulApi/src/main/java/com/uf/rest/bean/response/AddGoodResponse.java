package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class AddGoodResponse {
	private boolean success;
	private ResponseError error;
	private AddGoodResponseData data;
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
	public AddGoodResponseData getData() {
		return data;
	}
	public void setData(AddGoodResponseData data) {
		this.data = data;
	}
	
}
