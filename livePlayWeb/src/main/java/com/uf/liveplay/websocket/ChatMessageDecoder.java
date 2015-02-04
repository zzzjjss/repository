package com.uf.liveplay.websocket;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import net.sf.json.JSONObject;

public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ChatMessage decode(String arg0) throws DecodeException {
		System.out.println(arg0);
		JSONObject json=JSONObject.fromObject(arg0);
		return (ChatMessage)JSONObject.toBean(json,ChatMessage.class);
		
	}

	@Override
	public boolean willDecode(String arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
