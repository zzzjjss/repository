package com.quick3;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import com.quick3.mbean.LatestOpenResults;


public class Main {
	public static void main(String[] args) {
		
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		try{
			ObjectName name = new ObjectName("com.quick3:type=LatestOpenResult"); 
			mbs.registerMBean(new LatestOpenResults(), name);
			mbs.addNotificationListener(name, new MBeanNotificationListener(), null, null);
			Thread autoSyn=new Thread(new AutoSynch(),"auto synch");
			autoSyn.start();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
	}
}
