package com.uf.broadcast.bean.response;

import com.uf.broadcast.bean.ErrorInfo;


public class CommonResponse {
	private Boolean result;
	private ErrorInfo errorInfo;
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
  public ErrorInfo getErrorInfo() {
    return errorInfo;
  }
  public void setErrorInfo(ErrorInfo errorInfo) {
    this.errorInfo = errorInfo;
  }
   
	
	
}
