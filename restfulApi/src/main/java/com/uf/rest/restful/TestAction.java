package com.uf.rest.restful;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.uf.rest.bean.TestBean;
@Singleton
@Path("/test")
public class TestAction {
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/jsonTest")
	public String jsonTest(TestBean bean){
		System.out.println("---------------"+bean.getA());
		System.out.println("***********"+new String(bean.getImg()));
		return "{}";
	}
}		
