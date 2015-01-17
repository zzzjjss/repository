package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class GetClientVersionResponse {
	private boolean success;
	private ResponseError error;
	private GetClientVersionResponseData data;
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
	public GetClientVersionResponseData getData() {
		return data;
	}
	public void setData(GetClientVersionResponseData data) {
		this.data = data;
	}
	
}
