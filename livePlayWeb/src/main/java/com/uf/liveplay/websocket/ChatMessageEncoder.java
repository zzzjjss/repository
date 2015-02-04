package com.uf.liveplay.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import net.sf.json.JSONObject;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage>{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encode(ChatMessage arg0) throws EncodeException {
		System.out.println("out--->"+JSONObject.fromObject(arg0).toString());
		return JSONObject.fromObject(arg0).toString();
		
	}

}
