package com.uf.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("helloworld")
public class HelloRest {
	 public static final String CLICHED_MESSAGE = "Hello World!";
	 
	@Produces("text/plain")
	@GET
    public String getHello() {
        return CLICHED_MESSAGE;
    }
}
