package com.uf.rest.test;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import org.junit.BeforeClass;
import org.junit.Test;

import com.uf.rest.bean.request.UserLoginRequest;
import com.uf.rest.bean.response.UserLoginResponse;

public class ShopStatisticActionTest {
	public static String ip="localhost:8080";
	public static String token;
	@BeforeClass
	public static void setToken(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://"+ip+"/cleaner/shop/account/login");
		UserLoginRequest request=new UserLoginRequest();
		request.setName("helloShop");
		request.setPassword("helloShop");
		request.setP("1");
		UserLoginResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),UserLoginResponse.class);
		if(response.getData()!=null){
			token=response.getData().getToken();
		}
		System.out.println(JSONObject.fromObject(response).toString());
	}
	
	

}
