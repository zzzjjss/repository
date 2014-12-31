package com.uf.restful;

import java.util.UUID;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.uf.bean.Session;
import com.uf.util.CacheUtil;
@Singleton
@Path("/login")
public class Login {
	@Produces("text/plain")
	@GET
	@Path("/authenticate")
    public String authenticate(@QueryParam("userName")String userName,@QueryParam("userPwd")String pwd) {
		StringBuilder  result=new  StringBuilder();
		if(userName.equals("a")&&pwd.equals("a")){
			UUID  id=UUID.randomUUID();
			String uid=id.toString();
			Session session=new Session();
			session.addAttribute("userName", userName);
			CacheUtil.saveObj(uid, session);
			result.append("uid="+uid);
		}
        return result.toString();
    }
	
	@Produces("text/plain")
	@GET
	@Path("/logout")
    public String logout(@QueryParam("uid")String uid) {
		CacheUtil.removeObj(uid);
		return "ok";
	}
}
