package com.uf.rest.test;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class LoginRestfulTest {

	@Test
	public void testRegist() throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8080/restfulApi/restful/login/regist");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("name", "vip3"));
		nvps.add(new BasicNameValuePair("password", "vip3"));
		nvps.add(new BasicNameValuePair("type", "0"));
		nvps.add(new BasicNameValuePair("platform", "1"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse response2 = httpclient.execute(httpPost);
		HttpEntity entity=response2.getEntity();
		System.out.println(EntityUtils.toString(entity));
	}
	@Test
	public void testIsUserExist() throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8080/restfulApi/restful/login/isUserExist");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("name", "vip2"));
		nvps.add(new BasicNameValuePair("platform", "1"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse response2 = httpclient.execute(httpPost);
		HttpEntity entity=response2.getEntity();
		System.out.println(EntityUtils.toString(entity));
	}
	
	@Test
	public void testAuthenticateAndChangePsdAndLogout() throws Exception{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost("http://localhost:8080/restfulApi/restful/login/authenticate");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("name", "vip2"));
		nvps.add(new BasicNameValuePair("password", "vip3"));
		nvps.add(new BasicNameValuePair("platform", "1"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse response2 = httpclient.execute(httpPost);
		HttpEntity entity=response2.getEntity();
		String json=EntityUtils.toString(entity);
		System.out.println(json);
		JSONObject jb = JSONObject.fromObject(json);
		String token=(String)jb.getJSONObject("data").get("token");
		System.out.println("token--->"+token);
		
		HttpPost chpsd = new HttpPost("http://localhost:8080/restfulApi/restful/login/changePassword");
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		param.add(new BasicNameValuePair("token", token));
		param.add(new BasicNameValuePair("old_psd", "vip3"));
		param.add(new BasicNameValuePair("new_psd", "vip3"));
		chpsd.setEntity(new UrlEncodedFormEntity(param));
		CloseableHttpResponse response3 = httpclient.execute(chpsd);
		HttpEntity entity2=response3.getEntity();
		System.out.println(EntityUtils.toString(entity2));
		
		HttpPost logout = new HttpPost("http://localhost:8080/restfulApi/restful/login/logout");
		List<NameValuePair> param2 = new ArrayList<NameValuePair>();
		param2.add(new BasicNameValuePair("token", token));
		param2.add(new BasicNameValuePair("platform", "1"));
		logout.setEntity(new UrlEncodedFormEntity(param2));
		CloseableHttpResponse response4 = httpclient.execute(logout);
		HttpEntity entity4=response4.getEntity();
		System.out.println(EntityUtils.toString(entity4));
	}
	
	
	
	
	
}
