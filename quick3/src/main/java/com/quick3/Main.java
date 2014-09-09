package com.quick3;

import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Map;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanServer;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;

import com.quick3.bean.StatisticResult;
import com.quick3.mbean.LatestOpenResults;


public class Main {
	public static void main(String[] args) {
		
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		try{
			ObjectName name = new ObjectName("com.quick3:type=LatestOpenResult"); 
			mbs.registerMBean(new LatestOpenResults(), name);
			mbs.addNotificationListener(name, new NotificationListener() {
				
				public void handleNotification(Notification notification, Object arg1) {
					if(notification instanceof AttributeChangeNotification){
						AttributeChangeNotification noti=(AttributeChangeNotification)notification;
						List<OpenResult> newOpens =(List<OpenResult>)noti.getNewValue();	
						for(OpenResult open:newOpens){
							System.out.println(open.getDateIndex()+":"+open.getResult());
						}
					}
					StatisticsTool tool=new StatisticsTool();
					int days=0;
					Map<Integer,Float> allStatistic=tool.allDataStatistics();
					Map<Integer,Float> todayStatistic=tool.statisticsBeforToday(days);
					Map<Integer,StatisticResult> result=tool.getStatistic(allStatistic,todayStatistic,days);
					System.out.println("----------------------befor today "+days+"   begin--------------------");
					for(Integer key:result.keySet()){
						StatisticResult statistic=result.get(key);
						float  global=statistic.getGlobalPercent();
						float  daysPercent=statistic.getDaysPercent();
						float sub=global-daysPercent;
						float nextPercent=statistic.getNextOpenPercent();
						System.out.println(key+"---->("+global*100+"%-"+daysPercent*100+"% ="+sub*100+"% )--------->"+nextPercent*100+"%");
					}
					System.out.println("----------------------befor today "+days+" end--------------------");
				}
			}, null, null);
			
			Thread autoSyn=new Thread(new AutoSynch(),"auto synch");
			autoSyn.start();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
	}
}
