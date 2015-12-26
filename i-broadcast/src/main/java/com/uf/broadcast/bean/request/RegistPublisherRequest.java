package com.uf.broadcast.bean.request;

import com.uf.broadcast.entity.Organization;

public class RegistPublisherRequest {
  private Organization org;
  private String userName;
  private String password;
  
  public Organization getOrg() {
      return org;
  }

  public void setOrg(Organization org) {
      this.org = org;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
  
}
