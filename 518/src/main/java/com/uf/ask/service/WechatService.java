package com.uf.ask.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.uf.ask.bean.NewsResponse;
import com.uf.ask.bean.NewsResponse.Article;
import com.uf.ask.bean.Request;
import com.uf.ask.bean.TextRequest;
import com.uf.ask.entity.Product;

public class WechatService {
  public static final String WEB_SERVICE_ADDRESS="http://127.0.0.1";
  public static final String WEB_SERVICE_PUBLIC_ADDRESS="http://ec2-52-26-58-43.us-west-2.compute.amazonaws.com";
  public static final String DEFAULT_PIC_URL=WEB_SERVICE_PUBLIC_ADDRESS+"/test/pic/default.jpg";
  public static final String LINK=WEB_SERVICE_PUBLIC_ADDRESS+"/test/controller/index";
  private HttpClient client=HttpClientBuilder.create().build();
  private List<Product>  searchProduct(String keyword){
    String url=WEB_SERVICE_ADDRESS+"/test/controller/customer/queryProducts";
    String linkUrl=LINK+"?keyword="+keyword;
    HttpPost  queryProduct=new HttpPost(url);
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    NameValuePair np=new BasicNameValuePair("keyword",keyword);
    params.add(np);
    try {
      queryProduct.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
    } catch (UnsupportedEncodingException e1) {
      e1.printStackTrace();
    }
    List<Product> products=new  ArrayList<Product>();
    try {
      HttpResponse response=client.execute(queryProduct);
      if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
        InputStream input=response.getEntity().getContent();
        JsonParser parser=new  JsonParser();
        JsonElement jsonEle=parser.parse(new InputStreamReader(input,"UTF-8"));
        if(jsonEle!=null&&jsonEle.isJsonObject()){
          JsonObject  jsonObj=jsonEle.getAsJsonObject();
          JsonArray  datas=jsonObj.getAsJsonArray("pageData");
          if(datas!=null&&datas.size()>0){
            for(int i=0;i<datas.size();i++){
              JsonObject product=datas.get(i).getAsJsonObject();
              Product p=new Product();
              p.setId(product.get("id").getAsInt());
              p.setName(product.get("name").getAsString());
              JsonArray imgs=product.get("imgsPath").getAsJsonArray();
              if(imgs!=null&&imgs.size()>0){
                p.setPicUrl(WEB_SERVICE_PUBLIC_ADDRESS+imgs.get(0).getAsString());
              }
              p.setPrice(product.get("sellPrice").getAsFloat());
              p.setUrl(linkUrl);
              products.add(p);
            }
          }
        }
      
        
      }else{
        System.out.println(queryProduct.toString()+" response statusCode is "+response.getStatusLine().getStatusCode());
      }
      
    } catch (ClientProtocolException e) {
      
      e.printStackTrace();
    } catch (IOException e) {
      
      e.printStackTrace();
    }
    return products;
  }
  
  private  Request getMsgEntity(String strXml){  
    Request  res=null;
    if(Pattern.matches(".*MsgType.*text.*MsgType.*", strXml)){
      try {
        JAXBContext context=JAXBContext.newInstance(TextRequest.class);
        TextRequest request=(TextRequest)context.createUnmarshaller().unmarshal(new ByteArrayInputStream(strXml.getBytes("UTF-8")));
        res=request;
      } catch (Exception e) {
        e.printStackTrace();
      }
      
    }
    return res;  
  }  
  
  public void processWechatMsgToOutputStrem(String xml,OutputStream out){  
    Request request=getMsgEntity(xml);
    if(request instanceof TextRequest){
      TextRequest req=(TextRequest)request;
      List<Product> products=searchProduct(req.getContent());
      NewsResponse  response=new  NewsResponse();
      List<Article> arts=new ArrayList<NewsResponse.Article>();
      if(products!=null&&products.size()>0){
        response.setArticleCount(products.size());
        for(Product p:products){
          Article  article=new Article();
          article.setDescription("");
          article.setPicUrl(p.getPicUrl());
          article.setTitle(p.getName()+":"+p.getPrice());
          article.setUrl(p.getUrl());
          arts.add(article);
        }
      }else{
        response.setArticleCount(1);
        Article  article=new Article();
        article.setDescription("");
        article.setPicUrl(DEFAULT_PIC_URL);
        article.setTitle("没找到合适的商品，请扫二维码人工咨询！");
        article.setUrl(LINK);
        arts.add(article);
      }
      response.setArticles(arts);
      response.setCreateTime(new Date().getTime());
      response.setFromUserName(req.getToUserName());
      response.setToUserName(req.getFromUserName());
      try {
        JAXBContext context=JAXBContext.newInstance(NewsResponse.class);
        Marshaller marshaller=context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(response, new OutputStreamWriter(System.out, "UTF-8"));
        marshaller.marshal(response, new OutputStreamWriter(out, "UTF-8"));
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    } 
}  
  public static void main(String[] args) {
    WechatService  service=new WechatService();
    service.processWechatMsgToOutputStrem("<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName>"+
        "<CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[香砂六君丸]]></Content>"+
        "<MsgId>1234567890123456</MsgId></xml>",System.out);
  }
}
