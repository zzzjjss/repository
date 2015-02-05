package com.uf.liveplay.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
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
		System.out.println("websocket connetcion ....." + sessionId);
		User user = SessionCache.findUser(sessionId);
		try {
			if (user == null) {
				session.close();
			} else {
				AllOnlineUsers allOnlineUsers=new AllOnlineUsers();
				List<String> userNames=new ArrayList<String>();
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
				 allOnlineUsers.setMessageType("onlineUsers");
				 allOnlineUsers.setUserNames(userNames);
				session.getBasicRemote().sendText(JSONObject.fromObject(online).toString());
				session.getBasicRemote().sendText(JSONObject.fromObject(allOnlineUsers).toString());
				session.getUserProperties().put("sessionId", sessionId);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

    @OnMessage 
    public void onMessage(final Session session, final ChatMessage chatMessage) { 
        try { 
            for (Session s : session.getOpenSessions()) { 
            	
                if (s.isOpen()) { 
                    s.getBasicRemote().sendObject(chatMessage); 
                } 
            } 
        } catch (IOException | EncodeException e) { 
           System.out.println(e);
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
    
}
