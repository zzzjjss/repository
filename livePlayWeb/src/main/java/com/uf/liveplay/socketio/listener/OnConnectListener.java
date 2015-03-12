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
		Set<String> userNames = new HashSet<String>();
		if (user != null) {
			UserOnlineMessage online = new UserOnlineMessage();
			online.setUserName(user.getName());

			for (SocketIOClient c : server.getAllClients()) {
				if (c.isChannelOpen()) {
					User sessionUser = SessionCache.findUser((String) c
							.get("sessionId"));
					if (sessionUser != null) {
						userNames.add(sessionUser.getName());
					}
					c.sendEvent(Events.USER_ONLINE_EVENT, online);
				}
			}
			userNames.add(user.getName());
			allOnlineUsersMsg.setUserNames(userNames);
			client.sendEvent(Events.ALL_ON_LINE_USER_EVENT, allOnlineUsersMsg);
			client.set("sessionId", sessionId);
		} else {
			for (SocketIOClient c : server.getAllClients()) {
				if (c.isChannelOpen()) {
					User sessionUser = SessionCache.findUser((String) c
							.get("sessionId"));
					if (sessionUser != null) {
						userNames.add(sessionUser.getName());
					}
				}
			}
			allOnlineUsersMsg.setUserNames(userNames);
			client.sendEvent(Events.ALL_ON_LINE_USER_EVENT, allOnlineUsersMsg);
		}

	}

}
