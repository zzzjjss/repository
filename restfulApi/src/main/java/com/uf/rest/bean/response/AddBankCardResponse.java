package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class AddBankCardResponse {
	private boolean success;
	private ResponseError error;
	private AddBankCardResponseData data;
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
	public AddBankCardResponseData getData() {
		return data;
	}
	public void setData(AddBankCardResponseData data) {
		this.data = data;
	}
	
}
