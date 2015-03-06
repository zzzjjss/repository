package com.uf.liveplay.unit;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.uf.liveplay.websocket.MyWebSocket;
import com.uf.liveplay.websocket.RealtimeDataMessage;

public class ServletWebsocketBridge {
	public static MyWebSocket webSocket=null;
	
	static{
		ScheduledExecutorService service=Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				sendRealTimeData();
				
			}
		}, 1, 5, TimeUnit.SECONDS);
	}
	
	public static void shutupUserMouth(Integer userId){
		if(webSocket!=null){
			webSocket.shutupUserMouth(userId);
		}
	}
	
	public static void sendRealTimeData(){
		HttpClientBuilder clientBuilder=HttpClients.custom();
		CloseableHttpClient client=clientBuilder.build();
		//HttpGet  get=new HttpGet("http://www.ytxzhibo.com/room/1");
		try {
			//CloseableHttpResponse  rep=client.execute(get);
			RealtimeDataMessage message=new RealtimeDataMessage();
			//message.setMessage(EntityUtils.toString(rep.getEntity()));
			message.setMessage("test");
			webSocket.publishRealtimeData(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
