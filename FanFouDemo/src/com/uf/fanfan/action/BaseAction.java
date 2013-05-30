package com.uf.fanfan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
	protected WebApplicationContext appContext= WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
	protected HttpServletRequest request=ServletActionContext.getRequest();
	protected HttpServletResponse response=ServletActionContext.getResponse();
}
