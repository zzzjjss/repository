package com.uf.ask.bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsResponse extends Response {
  @XmlElement(name="ArticleCount")
  private Integer articleCount;
  @XmlElementWrapper(name="Articles")
  @XmlElement(name="item")
  private List<Article> articles=new ArrayList<NewsResponse.Article>();
  public NewsResponse(){
    this.setMsgType("news");
  }
  public Integer getArticleCount() {
    return articleCount;
  }

  public void setArticleCount(Integer articleCount) {
    this.articleCount = articleCount;
  }
  public List<Article> getArticles() {
    return articles;
  }

  public void setArticles(List<Article> articles) {
    this.articles = articles;
  }

  
  @XmlAccessorType(XmlAccessType.FIELD)
  public static class Article {
    @XmlElement(name="Title")
    private String title;
    @XmlElement(name="Description")
    private String description;
    @XmlElement(name="PicUrl")
    private String picUrl;
    @XmlElement(name="Url")
    private String url;

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getPicUrl() {
      return picUrl;
    }

    public void setPicUrl(String picUrl) {
      this.picUrl = picUrl;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

  }
}
