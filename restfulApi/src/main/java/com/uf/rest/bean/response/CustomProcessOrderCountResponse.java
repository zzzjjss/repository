package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class CustomProcessOrderCountResponse {
	private boolean success;
	private ResponseError error;
	private CustomProcessOrderCountResponseData data;
	public ResponseError getError() {
		return error;
	}
	public void setError(ResponseError error) {
		this.error = error;
	}
	public CustomProcessOrderCountResponseData getData() {
		return data;
	}
	public void setData(CustomProcessOrderCountResponseData data) {
		this.data = data;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
