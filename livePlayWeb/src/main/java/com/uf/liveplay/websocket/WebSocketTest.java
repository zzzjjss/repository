package com.uf.liveplay.websocket;

import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
@ServerEndpoint(value = "/chat",encoders =ChatMessageEncoder.class, decoders = ChatMessageDecoder.class)
public class WebSocketTest {
	private Set<Session> sessions=new HashSet<Session>();
	@OnOpen 
	public void open(final Session session) {
		System.out.println("open a conn.....");
		
	}

    @OnMessage 
    public void onMessage(final Session session,final ChatMessage chatMessage) { 
        System.out.println("get messs");
    }
    
    @OnClose
    public void onClose(final Session session){
    	
    System.out.println("close  con");
    }
    @OnError
    public void onError(Throwable exception, Session session){
    	System.out.println(exception);
    }
    
    
}
