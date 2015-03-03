package com.uf.liveplay.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import net.sf.json.JSONObject;

import com.uf.liveplay.entity.User;
import com.uf.liveplay.unit.SessionCache;

@ServerEndpoint(value = "/chat/{sessionId}", encoders =ChatMessageEncoder.class, decoders = ChatMessageDecoder.class) 
public class MyWebSocket {
	@OnOpen 
	public void open(final Session session,@PathParam("sessionId") final String sessionId) {
		User user = SessionCache.findUser(sessionId);
		try {
			AllOnlineUsersMessage allOnlineUsersMsg=new AllOnlineUsersMessage();
			Set<String> userNames=new HashSet<String>();
			if (user != null) {
				UserOnlineMessage online = new UserOnlineMessage();
				online.setUserName(user.getName());
				online.setMessageType("online");
				 for (Session s : session.getOpenSessions()) { 
	                if (s.isOpen()) { 
	                	User sessionUser = SessionCache.findUser((String)s.getUserProperties().get("sessionId"));
	                	if(sessionUser!=null){
	                		userNames.add(sessionUser.getName());
	                	}
	                    s.getBasicRemote().sendText(JSONObject.fromObject(online).toString()); 
	                } 
		          }
				 allOnlineUsersMsg.setMessageType("onlineUsers");
				 userNames.add(user.getName());
				 allOnlineUsersMsg.setUserNames(userNames);
				 session.getBasicRemote().sendText(JSONObject.fromObject(allOnlineUsersMsg).toString());
				 session.getUserProperties().put("sessionId", sessionId);
			}else{
				for (Session s : session.getOpenSessions()) {
					if (s.isOpen()) {
						User sessionUser = SessionCache.findUser((String) s.getUserProperties().get("sessionId"));
						if (sessionUser != null) {
							userNames.add(sessionUser.getName());
						}
					}
				}
				 allOnlineUsersMsg.setMessageType("onlineUsers");
				 allOnlineUsersMsg.setUserNames(userNames);
				 session.getBasicRemote().sendText(JSONObject.fromObject(allOnlineUsersMsg).toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

    @OnMessage 
    public void onMessage(final Session session, final ChatMessage chatMessage) { 
        try { 
        	//禁止游客发言
        	Object id=session.getUserProperties().get("sessionId");
        	if(id!=null){
        		for (Session s : session.getOpenSessions()) { 
                    if (s.isOpen()) { 
                        s.getBasicRemote().sendObject(chatMessage); 
                    } 
                }
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        } 
    }
    
    @OnClose
    public void onClose(final Session session){
    	
    	User user = SessionCache.findUser((String)session.getUserProperties().get("sessionId"));
		try {
			if (user != null) {
				UserOfflineMessage offline = new UserOfflineMessage();
				offline.setUserName(user.getName());
				offline.setMessageType("offline");
				 for (Session s : session.getOpenSessions()) { 
	                if (s.isOpen()) { 
	                    s.getBasicRemote().sendText(JSONObject.fromObject(offline).toString()); 
	                } 
		          } 
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    @OnError
    public void onError(Throwable exception, Session session){
    	System.out.println(exception);
    }
}
