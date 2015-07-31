package com.uf.broadcast.bean.response;

public class LoginResponse extends CommonResponse{
  private String sessionId;

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }
  
}
