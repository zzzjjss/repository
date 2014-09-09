package com.quick3;

import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class AutoSynch implements Runnable{

	public void run() {
		try{
			DataDao dao=new DataDao();
			List<OpenResult> lastDayData=dao.findLastDayData();
			SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd");
			Date lastDate=format.parse("2012-12-01");
			if(lastDayData!=null&&lastDayData.size()>0){
				OpenResult openRes=lastDayData.get(0);
				dao.deleteDataByDate(openRes.getOpendate());
				lastDate=openRes.getOpendate();
			}
			DataSynchronizer syn=new DataSynchronizer();
			Date today=new Date(System.currentTimeMillis());
			syn.synchAndSaveData(lastDate, today);
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			ObjectName name = new ObjectName("com.quick3:type=LatestOpenResult"); 
			mbs.setAttribute(name,new Attribute("LatestOpenResults", dao.findLastDayData()));
			while(true){
				try{
					today=new Date(System.currentTimeMillis());
					Map<Integer,OpenResult> savedResult=dao.findOneDayData(today);
					List<OpenResult> todayOpens=syn.synchOneDateSeqData(today);
					List<OpenResult> newOpens=new ArrayList<OpenResult>();
					for(OpenResult  r:todayOpens){
						OpenResult saved=savedResult.get(r.getDateIndex());
						if(saved==null){
							System.out.println("add new open result ,data index is :"+r.getDateIndex());
							dao.insertOpenResult(r);
							newOpens.add(r);
						}
					}
					if(newOpens.size()>0){
						mbs.setAttribute(name,new Attribute("LatestOpenResults", newOpens));
					}
					
					Thread.sleep(5*60*1000);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
