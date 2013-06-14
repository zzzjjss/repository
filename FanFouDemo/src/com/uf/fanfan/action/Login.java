package com.uf.fanfan.action;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uf.fanfan.service.LoginService;

public class Login extends BaseAction{
	LoginService loginService=(LoginService)appContext.getBean("loginService");
	Logger log = LoggerFactory.getLogger(Login.class);
	
	private String userName;
	private String password;
	private String userType;
	private String verifyCode;
	
	
	public String login(){
		try{
			String remoteIp=request.getRemoteAddr();
			Map<String,Long[]> loginRecord=(Map<String, Long[]>)request.getServletContext().getAttribute("remoteAddr");
			//preLoginTime[0] is the logintime ,preLoginTime[1] is the login 次数
			Long preLoginTime[]=loginRecord.get(remoteIp);
			if(preLoginTime==null){
				preLoginTime=new Long[2];
				preLoginTime[0]=System.currentTimeMillis();
				preLoginTime[1]=1L;
				loginRecord.put(remoteIp, preLoginTime);
			}else{
				long interval=System.currentTimeMillis()-preLoginTime[0];
				preLoginTime[1]=preLoginTime[1].longValue()+1;
				if(interval<3600&&preLoginTime[1]>5){
					if(!verifyCode.equalsIgnoreCase((String)session.getAttribute("verifyCode"))){
						writeResultToClient("text/paint", "verifyCodeError");
						return null;
					}
				}
			}
			boolean res=loginService.login(userName, password, userType);
			if(res)
				this.writeResultToClient("text/plain", "success");
			else{
				if(preLoginTime[1]>4)
					writeResultToClient("text/paint", "getVerrfyCode");
				else
					writeResultToClient("text/paint", "fail");
			}
				
		}catch(Exception e){
			log.error("login error", e);
			writeResultToClient("text/paint", "error");
		}
		return null;
		
	}
	public String generateVerifyCode(){
		
		return null;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	
}
