package com.uf.broadcast.test;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.uf.broadcast.bean.request.AddOrganizationRequest;
import com.uf.broadcast.bean.request.RegistPublisherRequest;
import com.uf.broadcast.bean.response.CommonResponse;
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
      org.setAddress("Î÷ºþ");
      org.setLocationX(11.3f);
      org.setLocationY(344.4f);
      org.setName("Ð¡´Ç¹¤");
      org.setPointAmount(100);
      request.setOrg(org);
      request.setUserName("jason");
      request.setPassword("zhang");
      CommonResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),CommonResponse.class);
      Gson gson = new Gson();
      System.out.println(gson.toJson(response));
  }
  
  
}
