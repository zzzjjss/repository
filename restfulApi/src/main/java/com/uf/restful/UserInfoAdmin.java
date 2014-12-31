package com.uf.restful;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.uf.bean.Session;
import com.uf.util.CacheUtil;

@Singleton
@Path("/user")
public class UserInfoAdmin {
	@Produces("text/plain")
	@GET
	@Path("/getUserInfo")
    public String logout(@QueryParam("uid")String uid) {
		Session session=(Session)CacheUtil.getObj(uid);
		return (String)session.getAttribute("userName");
	}
}
