package com.uf.liveplay.unit;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RegistFilter {
	private static ConcurrentHashMap<String, Long> ip_regist=new ConcurrentHashMap<String, Long>();
	public static int INTERVAL_SECOND=60;
	static{
		ScheduledExecutorService service=Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				cleanRegisterIp();
				
			}
		}, 1, 5, TimeUnit.SECONDS);
	}
	public static boolean isIpCanRegist(String ip){
		Long time=ip_regist.get(ip);
		if(time==null){
			ip_regist.put(ip, System.currentTimeMillis());
			return true;
		}else{
			long current=System.currentTimeMillis();
			long dis=current-time;
			if(dis>1000*INTERVAL_SECOND){
				ip_regist.put(ip, current);
				return true;
			}else{
				return false;
			}
		}
	}
	public static void cleanRegisterIp(){
		
		for(String ip:ip_regist.keySet()){
			Long preTime=ip_regist.get(ip);
			long current=System.currentTimeMillis();
			if(current-preTime>1000*INTERVAL_SECOND){
				ip_regist.remove(ip);
				System.out.println("remove the ip:"+ip);
			}
		}
	}
}
