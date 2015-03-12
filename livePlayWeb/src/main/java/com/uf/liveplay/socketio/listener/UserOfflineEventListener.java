package com.uf.liveplay.socketio.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.uf.liveplay.socketio.message.UserOfflineMessage;

public class UserOfflineEventListener implements DataListener<UserOfflineMessage>{
	
	SocketIOServer server;

    public UserOfflineEventListener(SocketIOServer server) {
        this.server = server;
    }

	@Override
	public void onData(SocketIOClient arg0, UserOfflineMessage arg1,
			AckRequest arg2) throws Exception {
		// TODO Auto-generated method stub
		
	}

	

	

}
