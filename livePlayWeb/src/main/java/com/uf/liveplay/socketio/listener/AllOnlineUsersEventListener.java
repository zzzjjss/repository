package com.uf.liveplay.socketio.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.uf.liveplay.socketio.message.AllOnlineUsersMessage;

public class AllOnlineUsersEventListener implements DataListener<AllOnlineUsersMessage>{
	
	
	SocketIOServer server;

    public AllOnlineUsersEventListener(SocketIOServer server) {
        this.server = server;
    }

	@Override
	public void onData(SocketIOClient arg0, AllOnlineUsersMessage arg1,
			AckRequest arg2) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
