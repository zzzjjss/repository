package com.uf.liveplay.unit;

import com.uf.liveplay.websocket.MyWebSocket;

public class ServletWebsocketBridge {
	public static MyWebSocket webSocket=null;
	
	
	public static void shutupUserMouth(Integer userId){
		if(webSocket!=null){
			webSocket.shutupUserMouth(userId);
		}
	} 
}
