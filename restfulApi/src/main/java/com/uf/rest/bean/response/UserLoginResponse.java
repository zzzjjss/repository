package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class UserLoginResponse {
	private boolean success;
	private ResponseError error;
	private UserLoginResponseData data;
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
	public UserLoginResponseData getData() {
		return data;
	}
	public void setData(UserLoginResponseData data) {
		this.data = data;
	}
	
}
