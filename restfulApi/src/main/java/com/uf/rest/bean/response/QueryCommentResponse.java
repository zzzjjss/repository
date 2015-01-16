package com.uf.rest.bean.response;

import com.uf.rest.bean.ResponseError;

public class QueryCommentResponse {
	private boolean success;
	private ResponseError error;
	private QueryCommentResponseData data;
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
	public QueryCommentResponseData getData() {
		return data;
	}
	public void setData(QueryCommentResponseData data) {
		this.data = data;
	}
	
}
