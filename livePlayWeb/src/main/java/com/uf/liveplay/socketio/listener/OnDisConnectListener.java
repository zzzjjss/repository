package com.uf.liveplay.socketio.listener;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.Session;

import net.sf.json.JSONObject;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.uf.liveplay.entity.User;
import com.uf.liveplay.socketio.Events;
import com.uf.liveplay.socketio.message.AllOnlineUsersMessage;
import com.uf.liveplay.socketio.message.AllUnknowUserCountMessage;
import com.uf.liveplay.socketio.message.UserOfflineMessage;
import com.uf.liveplay.unit.SessionCache;

public class OnDisConnectListener implements DisconnectListener{
	SocketIOServer server;
	public OnDisConnectListener(SocketIOServer server){
		this.server=server;
	}
	
	
	@Override
	public void onDisconnect(SocketIOClient client) {
		Object sessionId = client.get("sessionId");
		User user = null;
		if (sessionId != null) {
			user = SessionCache.findUser((String) sessionId);
		}
		if (user != null) {
			AllOnlineUsersMessage allOnlineUsersMsg = new AllOnlineUsersMessage();
			Set<String> userNames = new HashSet<String>();
			for (SocketIOClient c : server.getAllClients()) {
				Object clientSessionId= c.get("sessionId");
				if(clientSessionId!=null){
					User sessionUser = SessionCache.findUser((String)clientSessionId);
					if (sessionUser != null) {
						userNames.add(sessionUser.getName());
					}
				}
			}
			allOnlineUsersMsg.setUserNames(userNames);
			server.getBroadcastOperations().sendEvent(Events.ALL_ON_LINE_USER_EVENT, allOnlineUsersMsg);
			
		}else if(sessionId!=null&&"unknow".equals(sessionId)){
			int unKnowCount=0;
			for (SocketIOClient c : server.getAllClients()) {
				Object clientSessionId= c.get("sessionId");
				if(clientSessionId!=null&&"unknow".equals((String)clientSessionId)){
					unKnowCount++;
				}
			}
			AllUnknowUserCountMessage allUnKnow=new AllUnknowUserCountMessage();
			allUnKnow.setCount(unKnowCount);
			server.getBroadcastOperations().sendEvent(Events.ALL_UNKNOW_COUNT, allUnKnow);
		}

	}

}
