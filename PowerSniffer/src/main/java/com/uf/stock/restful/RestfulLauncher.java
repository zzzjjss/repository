package com.uf.stock.restful;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class RestfulLauncher extends ResourceConfig{
  public RestfulLauncher() {
    packages("com.uf.stock.restful.action");
}
}
