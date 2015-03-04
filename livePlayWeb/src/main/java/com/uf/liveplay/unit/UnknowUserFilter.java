package com.uf.liveplay.unit;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UnknowUserFilter {

	private static ConcurrentHashMap<String, Long> ipStartTime=new ConcurrentHashMap<String, Long>();
	public static int INTERVAL_SECOND=60*60*12;
	private static int ALLOW_LISTEN_SECOND=60*20;
	static{
		ScheduledExecutorService service=Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				cleanRegisterIp();
				
			}
		}, 1, 5, TimeUnit.SECONDS);
	}
	public UnknowUserFilter(int allowListenSecond,int intervalSecond){
		INTERVAL_SECOND=intervalSecond;
		ALLOW_LISTEN_SECOND=allowListenSecond;
	}
	public  boolean isIpCanListener(String ip){
		Long time=ipStartTime.get(ip);
		if(time==null){
			ipStartTime.put(ip, System.currentTimeMillis());
			return true;
		}else{
			long current=System.currentTimeMillis();
			long dis=current-time;
			if(dis>=1000*INTERVAL_SECOND){
				ipStartTime.put(ip, current);
				return true;
			}else if(dis<=ALLOW_LISTEN_SECOND*1000){
				return true;
			}else{
				return false;
			}
		}
	}
	public static void cleanRegisterIp(){
		
		for(String ip:ipStartTime.keySet()){
			Long preTime=ipStartTime.get(ip);
			long current=System.currentTimeMillis();
			if(current-preTime>1000*INTERVAL_SECOND){
				ipStartTime.remove(ip);
				System.out.println("remove the ip:"+ip);
			}
		}
	}

}
