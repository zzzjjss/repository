package com.uf;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("restful")
public class MyApplication extends ResourceConfig{
	 public MyApplication() {
	        packages("com.uf.restful");
	    }
}
