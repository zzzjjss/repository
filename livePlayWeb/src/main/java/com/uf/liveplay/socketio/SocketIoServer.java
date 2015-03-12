package com.uf.liveplay.socketio;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.uf.liveplay.socketio.listener.AllOnlineUsersEventListener;
import com.uf.liveplay.socketio.listener.ChatMessageEventListener;
import com.uf.liveplay.socketio.listener.OnConnectListener;
import com.uf.liveplay.socketio.listener.OnDisConnectListener;
import com.uf.liveplay.socketio.listener.ShutupEventListener;
import com.uf.liveplay.socketio.listener.UserOfflineEventListener;
import com.uf.liveplay.socketio.listener.UserOnlineEventListener;
import com.uf.liveplay.socketio.message.AllOnlineUsersMessage;
import com.uf.liveplay.socketio.message.ChatMessage;
import com.uf.liveplay.socketio.message.ShutupMessage;
import com.uf.liveplay.socketio.message.UserOfflineMessage;
import com.uf.liveplay.socketio.message.UserOnlineMessage;

public class SocketIoServer {
	private String hostName;
	private int port=81;
	
	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}

	public SocketIoServer(){
		Configuration config = new Configuration();
	    config.setHostname(hostName);
	    config.setPort(port);
	    SocketIOServer server = new SocketIOServer(config);
	    ChatMessageEventListener listener = new ChatMessageEventListener(server);
	    AllOnlineUsersEventListener allOnlineListener=new AllOnlineUsersEventListener(server);
	    ShutupEventListener  shutupListener=new ShutupEventListener(server);
	    UserOfflineEventListener offlienListener=new UserOfflineEventListener(server);
	    UserOnlineEventListener onlineListener=new UserOnlineEventListener(server);
	    OnConnectListener onConnListener=new OnConnectListener(server); 
	    OnDisConnectListener disConListener=new OnDisConnectListener(server); 
        server.addEventListener(Events.CHAT_MESSAGE_EVENT, ChatMessage.class, listener);
        server.addEventListener(Events.ALL_ON_LINE_USER_EVENT, AllOnlineUsersMessage.class, allOnlineListener);
        server.addEventListener(Events.SHUTUP_EVENT, ShutupMessage.class, shutupListener);
        server.addEventListener(Events.USER_OFFLINE_EVENT, UserOfflineMessage.class, offlienListener);
        server.addEventListener(Events.USER_ONLINE_EVENT, UserOnlineMessage.class, onlineListener);
        server.addConnectListener(onConnListener);
        server.addDisconnectListener(disConListener);
        server.start();
	}
}
