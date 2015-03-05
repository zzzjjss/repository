package com.uf.liveplay.websocket;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import com.uf.liveplay.unit.ServletWebsocketBridge;

public class CustomConfigurator extends ServerEndpointConfig.Configurator{
	@Override
    public void modifyHandshake(ServerEndpointConfig config, 
                                HandshakeRequest request, 
                                HandshakeResponse response)
    {
        HttpSession httpSession = (HttpSession)request.getHttpSession();
        if(httpSession!=null){
        	config.getUserProperties().put(HttpSession.class.getName(),httpSession);
        }
        
    }
	
	 @Override
	    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException
	    {
	        T endpoint = super.getEndpointInstance(endpointClass);

	        if (endpoint instanceof MyWebSocket){
	        	MyWebSocket ws=(MyWebSocket)endpoint;
	        	ServletWebsocketBridge.webSocket=ws;
	        	System.out.println("ws................");
	        }	
	        return endpoint;
	    }
}
