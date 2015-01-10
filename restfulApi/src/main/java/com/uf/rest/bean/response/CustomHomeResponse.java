package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class CustomHomeResponse {
	private boolean success;
	private ResponseError error;
	private CustomHomeResponseData data;
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
	public CustomHomeResponseData getData() {
		return data;
	}
	public void setData(CustomHomeResponseData data) {
		this.data = data;
	}
	
}
