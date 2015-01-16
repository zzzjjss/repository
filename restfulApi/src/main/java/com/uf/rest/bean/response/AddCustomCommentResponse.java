package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class AddCustomCommentResponse {
	private boolean success;
	private ResponseError error;
	private AddCustomCommentResponseData data;
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
	public AddCustomCommentResponseData getData() {
		return data;
	}
	public void setData(AddCustomCommentResponseData data) {
		this.data = data;
	}
	
}
