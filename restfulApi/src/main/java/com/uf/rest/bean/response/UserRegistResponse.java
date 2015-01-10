package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class UserRegistResponse {
	private boolean success;
	private ResponseError error;
	private UserRegistResponseData data;
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
	public UserRegistResponseData getData() {
		return data;
	}
	public void setData(UserRegistResponseData data) {
		this.data = data;
	}
	
}
