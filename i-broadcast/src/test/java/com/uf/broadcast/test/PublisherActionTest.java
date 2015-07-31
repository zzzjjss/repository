package com.uf.broadcast.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.uf.broadcast.bean.request.PublishMessageRequest;
import com.uf.broadcast.bean.request.PublisherLoginRequest;
import com.uf.broadcast.bean.request.RegistPublisherRequest;
import com.uf.broadcast.bean.response.CommonResponse;
import com.uf.broadcast.bean.response.LoginResponse;
import com.uf.broadcast.entity.Organization;

public class PublisherActionTest {

  private static String token;
  public static String ip="localhost:8080";
  @BeforeClass
  public static void setToken(){
      
  }
  
  @Test
  public void testRegist() throws Exception{
      Client client = ClientBuilder.newClient();
      WebTarget target = client.target("http://"+ip+"/i-broadcast/publisher/registPublisher");
      RegistPublisherRequest request=new RegistPublisherRequest();
      Organization org=new Organization();
      org.setAddress("西湖");
      org.setLocationX(11.3f);
      org.setLocationY(344.4f);
      org.setName("小辞工");
      org.setPointAmount(100);
      request.setOrg(org);
      request.setUserName("jason");
      request.setPassword("zhang");
      CommonResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),CommonResponse.class);
      Gson gson = new Gson();
      System.out.println(gson.toJson(response));
  }
  
  @Test
  public void testPublishMessage() throws Exception{
    Gson gson = new Gson();
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://"+ip+"/i-broadcast/publisher/publisherLogin");
    PublisherLoginRequest request=new PublisherLoginRequest();
    request.setUserName("jason");
    request.setPassword("zhang");
    request.setVerifyCode("zdba");
    LoginResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),LoginResponse.class);
    System.out.println(gson.toJson(response));
    if(response.getResult()==true){
      String sessionId=response.getSessionId();
      WebTarget publishMsg = client.target("http://"+ip+"/i-broadcast/publisher/publishMessage");
      PublishMessageRequest pubReq=new PublishMessageRequest();
      pubReq.setSession(sessionId);
      pubReq.setContent("小吃嘻嘻开张");
      CommonResponse pubResponse=publishMsg.request(MediaType.APPLICATION_JSON).post(Entity.entity(pubReq, MediaType.APPLICATION_JSON),CommonResponse.class);
      System.out.println(gson.toJson(pubResponse));
    }
    
    
  }
  
}
