package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class UserChangePasswordResponse {
	private boolean success;
	private ResponseError error;
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
}
