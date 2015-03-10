package com.uf.liveplay.unit;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketPoliceFile {
	public SocketPoliceFile(){
			Thread t=new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						ServerSocket server=new ServerSocket(843);
						while(true){
							System.out.println("start listen 843");
							Socket socket=server.accept();
							System.out.println("a client con.....");
							byte inContent[]=new byte[15];
							socket.getInputStream().read(inContent);
							System.out.println(new String(inContent));
							socket.getOutputStream();
							String outContent="<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"*\" /></cross-domain-policy>";
							socket.getOutputStream().write(outContent.getBytes());
							socket.close();
							System.out.println("close a  con.");
						}
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
			});
			t.start();
		
	}
}
