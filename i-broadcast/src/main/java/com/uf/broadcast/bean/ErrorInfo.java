package com.uf.broadcast.bean;

public class ErrorInfo {
  
  public static final ErrorInfo SYSTEM_EXCEPTION=new ErrorInfo("20001","ϵͳ�쳣");  
  public static final ErrorInfo NO_LOGIN=new ErrorInfo("2002","û��¼");  
  public static final ErrorInfo DATA_EXIST=new ErrorInfo("2003","�����Ѿ�����");  
  
  private String code,errorMessage;
  public ErrorInfo(){
    
  }
  public ErrorInfo(String code,String errorMessage){
    this.code=code;
    this.errorMessage=errorMessage;
  }
  
  public void setCode(String code) {
    this.code = code;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getCode() {
    return code;
  }
  public String getErrorMessage() {
    return errorMessage;
  }
  
}
