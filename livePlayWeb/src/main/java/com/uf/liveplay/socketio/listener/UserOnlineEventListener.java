package com.uf.liveplay.socketio.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.uf.liveplay.socketio.message.UserOnlineMessage;

public class UserOnlineEventListener implements DataListener<UserOnlineMessage>{
	
	SocketIOServer server;

    public UserOnlineEventListener(SocketIOServer server) {
        this.server = server;
    }

	@Override
	public void onData(SocketIOClient arg0, UserOnlineMessage arg1,
			AckRequest arg2) throws Exception {
		
	}

	

	

}
