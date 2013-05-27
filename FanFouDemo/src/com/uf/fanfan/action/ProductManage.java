package com.uf.fanfan.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

public class ProductManage extends ActionSupport{
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
}
