package com.uf.liveplay.websocket;

import java.io.IOException;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat/{room}", encoders =ChatMessageEncoder.class, decoders = ChatMessageDecoder.class) 
public class MyWebSocket {
	@OnOpen 
    public void open(final Session session, @PathParam("room") final String room) { 
		System.out.println("websocket connetcion ....."+room);
        session.getUserProperties().put("room", room); 
    } 
  
    @OnMessage 
    public void onMessage(final Session session, final ChatMessage chatMessage) { 
        String room = (String) session.getUserProperties().get("room"); 
        try { 
            for (Session s : session.getOpenSessions()) { 
            	
                if (s.isOpen() && room.equals(s.getUserProperties().get("room"))) { 
                    s.getBasicRemote().sendObject(chatMessage); 
                } 
            } 
        } catch (IOException | EncodeException e) { 
           System.out.println(e);
        } 
    } 
    @OnClose
    public void onClose(final Session session){
    	
    }
    
}
