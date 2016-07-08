package com.uf.ask.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class TextRequest extends Request{
  @XmlElement(name="Content")
  private String content;
  
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
  
}
