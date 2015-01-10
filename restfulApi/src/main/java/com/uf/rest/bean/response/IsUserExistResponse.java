package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class IsUserExistResponse{
	private boolean success;
	private ResponseError error;
	private IsUserExistResponseData data;
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
	public IsUserExistResponseData getData() {
		return data;
	}
	public void setData(IsUserExistResponseData data) {
		this.data = data;
	}
	
	
}
