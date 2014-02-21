package com.uf.fanfan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
	protected WebApplicationContext appContext= WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
	protected HttpServletRequest request=ServletActionContext.getRequest();
	protected HttpServletResponse response=ServletActionContext.getResponse();
	protected HttpSession session=ServletActionContext.getRequest().getSession();
	Logger log = LoggerFactory.getLogger(BaseAction.class);
	protected void writeResultToClient(String contentType,String result) {
		response.setContentType(contentType);
		response.setCharacterEncoding("utf-8");
		try {
			response.getOutputStream().write(result.getBytes("utf-8"));
		} catch (Exception e) {
			log.error("write response error", e);
		} 
		
	}
}
