package com.uf.liveplay.socketio.listener;

import javax.websocket.Session;

import net.sf.json.JSONObject;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.uf.liveplay.socketio.Events;
import com.uf.liveplay.socketio.message.ChatMessage;

public class ChatMessageEventListener implements DataListener<ChatMessage>{
	
	
	SocketIOServer server;

    public ChatMessageEventListener(SocketIOServer server) {
        this.server = server;
    }
	@Override
	public void onData(SocketIOClient client, ChatMessage data, AckRequest ackSender)
			throws Exception {
		 System.out.println(JSONObject.fromObject(data).toString());
		 try { 
	        	//禁止游客发言
	        	Object id=client.get("sessionId");
	        	if(id!=null){
	        		server.getBroadcastOperations().sendEvent(Events.CHAT_MESSAGE_EVENT, data);
	        	}
	        } catch (Exception e) {
	        	e.printStackTrace();
	        } 
		 
		 
		 
	}

}
