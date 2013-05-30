package com.uf.fanfan.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

public class ProductManage extends BaseAction{
	 Logger log=LoggerFactory.getLogger(ProductManage.class);
	public String addProduct(){
		log.info("add product ");
		return SUCCESS;
	}
	public String delProduct(){
		return "ajaxSuccess";
	}
	public String modifyProduct(){
		return SUCCESS;
	}
	public String getPageShopProducts(){
		log.info("getPageShopProducts");
		String res="{\"page\":1,\"total\":239,\"rows\":[{\"id\":\"1\",\"cell\":[\"土豆烧牛肉\",\"￥15.00\",\"100\",\"2013-01-01\"]},{\"id\":\"2\",\"cell\":[\"土豆烧鸡\",\"￥15.00\",\"103\",\"2013-01-02\"]}]}";
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		try {
			response.getOutputStream().write(res.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			log.error("", e);
		} catch (IOException e) {
			log.error("", e);
		}
		return null;
	}
}
