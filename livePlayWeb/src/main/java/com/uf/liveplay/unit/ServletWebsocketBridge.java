package com.uf.liveplay.unit;

import com.uf.liveplay.websocket.MyWebSocket;

public class ServletWebsocketBridge {
	public static MyWebSocket webSocket=null;
	
//	static{
//		ScheduledExecutorService service=Executors.newSingleThreadScheduledExecutor();
//		service.scheduleAtFixedRate(new Runnable() {
//			
//			@Override
//			public void run() {
//				sendRealTimeData();
//				
//			}
//		}, 1, 5, TimeUnit.SECONDS);
//	}
	
	public static void shutupUserMouth(Integer userId){
		if(webSocket!=null){
			webSocket.shutupUserMouth(userId);
		}
	}
	
	public static void sendRealTimeData(){
		
	}
	
}
