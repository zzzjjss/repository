package com.uf.rest.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONObject;

import org.junit.Test;

import com.uf.rest.bean.request.CustomHomeRequest;
import com.uf.rest.bean.request.RegistUserRequest;
import com.uf.rest.bean.request.UserChangePasswordRequest;
import com.uf.rest.bean.request.UserLoginRequest;
import com.uf.rest.bean.request.UserLogoutRequest;
import com.uf.rest.bean.response.CustomHomeResponse;
import com.uf.rest.bean.response.IsUserExistResponse;
import com.uf.rest.bean.response.UserChangePasswordResponse;
import com.uf.rest.bean.response.UserLoginResponse;
import com.uf.rest.bean.response.UserLogoutResponse;
import com.uf.rest.bean.response.UserRegistResponse;

public class CustomActionTest {

	@Test
	public void testRegist() throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/cleaner/custom/account/register");
		RegistUserRequest request =new RegistUserRequest(); 
		request.setName("hello1");
		request.setP(1);
		request.setPassword("hello");
		request.setType(0);
		UserRegistResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),UserRegistResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	@Test
	public void testIsUserExist() throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/custom/account/existed?name=hello");
		IsUserExistResponse response=target.request(MediaType.APPLICATION_JSON).get(IsUserExistResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
		
	}
	
	@Test
	public void testAuthenticateAndChangePsdAndLogout() throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/custom/account/login");
		UserLoginRequest request=new UserLoginRequest();
		request.setName("hello");
		request.setPassword("hello");
		request.setP("1");
		UserLoginResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),UserLoginResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
		
		Client client1 = ClientBuilder.newClient();
		WebTarget target1 = client1.target("http://localhost:8080/custom/account/password");
		UserChangePasswordRequest request1=new UserChangePasswordRequest();
		request1.setToken(response.getData().getToken());
		request1.setNew_psd("hello");
		request1.setOld_psd("hello");
		UserChangePasswordResponse response1=target1.request(MediaType.APPLICATION_JSON).post(Entity.entity(request1, MediaType.APPLICATION_JSON),UserChangePasswordResponse.class);
		System.out.println(JSONObject.fromObject(response1).toString());
		
		Client client2 = ClientBuilder.newClient();
		WebTarget target2 = client2.target("http://localhost:8080/custom/account/logout");
		UserLogoutRequest request3=new UserLogoutRequest();
		request3.setToken(response.getData().getToken());
		UserLogoutResponse response3=target2.request(MediaType.APPLICATION_JSON).post(Entity.entity(request3, MediaType.APPLICATION_JSON),UserLogoutResponse.class);
		System.out.println(JSONObject.fromObject(response3).toString());
		
	}
	
	
	@Test
	public void testCustomHome() throws Exception{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080/cleaner/custom/home");
		CustomHomeRequest request=new CustomHomeRequest();
		request.setP(0);
		request.setLatitude(11.1d);
		request.setLongitude(333.3d);
		request.setCount(10);
		request.setStart(0);
		request.setToken("tt");
		CustomHomeResponse response=target.request(MediaType.APPLICATION_JSON).post(Entity.entity(request, MediaType.APPLICATION_JSON),CustomHomeResponse.class);
		System.out.println(JSONObject.fromObject(response).toString());
	}
	
	
}
