package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class DrawDetailResponse {
	private boolean success;
	private ResponseError error;
	private DrawDetailResponseData data;
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
	public DrawDetailResponseData getData() {
		return data;
	}
	public void setData(DrawDetailResponseData data) {
		this.data = data;
	}
	
}
