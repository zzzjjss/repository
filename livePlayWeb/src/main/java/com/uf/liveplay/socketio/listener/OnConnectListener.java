package com.uf.liveplay.socketio.listener;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.Session;

import net.sf.json.JSONObject;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.uf.liveplay.entity.User;
import com.uf.liveplay.socketio.Events;
import com.uf.liveplay.socketio.message.AllOnlineUsersMessage;
import com.uf.liveplay.socketio.message.AllUnknowUserCountMessage;
import com.uf.liveplay.socketio.message.UnknowUserOnlineMessage;
import com.uf.liveplay.socketio.message.UserOnlineMessage;
import com.uf.liveplay.unit.SessionCache;

public class OnConnectListener implements ConnectListener{
	SocketIOServer server;
	public OnConnectListener(SocketIOServer server){
		this.server=server;
	}
	@Override
	public void onConnect(SocketIOClient client) {
		String sessionId = client.getHandshakeData().getSingleUrlParam("sessionId");
		User user = SessionCache.findUser(sessionId);
		AllOnlineUsersMessage allOnlineUsersMsg = new AllOnlineUsersMessage();
		int unKnowCount=0;
		Set<String> userNames = new HashSet<String>();
		if (user != null) {
			//UserOnlineMessage online = new UserOnlineMessage();
			//online.setUserName(user.getName());
			//server.getBroadcastOperations().sendEvent(Events.USER_ONLINE_EVENT, online);
			userNames.add(user.getName());
			client.set("sessionId", sessionId);
		} else {
			if("unknow".equals(sessionId)){
				client.set("sessionId", "unknow");
				unKnowCount++;
			}
		}
			
		for (SocketIOClient c : server.getAllClients()) {
			if(c.isChannelOpen()){
				Object clientSessionId= c.get("sessionId");
				if(clientSessionId!=null){
					User sessionUser = SessionCache.findUser((String)clientSessionId);
					if (sessionUser != null) {
						userNames.add(sessionUser.getName());
					}else if("unknow".equals(clientSessionId)){
						unKnowCount++;
					}
				}
			}
		}
		AllUnknowUserCountMessage allUnKnow=new AllUnknowUserCountMessage();
		allUnKnow.setCount(unKnowCount);
		server.getBroadcastOperations().sendEvent(Events.ALL_UNKNOW_COUNT, allUnKnow);
		allOnlineUsersMsg.setUserNames(userNames);
		server.getBroadcastOperations().sendEvent(Events.ALL_ON_LINE_USER_EVENT, allOnlineUsersMsg);
	}

}
