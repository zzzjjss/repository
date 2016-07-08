package com.uf.ask.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Response {
  @XmlElement(name="ToUserName")
  private String toUserName;
  @XmlElement(name="FromUserName")
  private String fromUserName;
  @XmlElement(name="CreateTime")
  private Long createTime;
  @XmlElement(name="MsgType")
  private String msgType;
  public String getToUserName() {
    return toUserName;
  }
  public void setToUserName(String toUserName) {
    this.toUserName = toUserName;
  }
  public String getFromUserName() {
    return fromUserName;
  }
  public void setFromUserName(String fromUserName) {
    this.fromUserName = fromUserName;
  }
  
  public Long getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }
  public String getMsgType() {
    return msgType;
  }
  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }
  
}
