package com.uf.stock.restful.bean;

public class RestfulResponse {
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
