package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class AddAddressResponse {
	private boolean success;
	private ResponseError error;
	private AddAddressResponseData data;
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
	public AddAddressResponseData getData() {
		return data;
	}
	public void setData(AddAddressResponseData data) {
		this.data = data;
	}
	
}
