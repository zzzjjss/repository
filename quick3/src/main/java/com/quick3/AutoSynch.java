package com.quick3;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
			StatisticsTool tool=new StatisticsTool();
			while(true){
				try{
					today=new Date(System.currentTimeMillis());
					Map<Integer,OpenResult> savedResult=dao.findOneDayData(today);
					List<OpenResult> todayOpens=syn.synchOneDateSeqData(today);
					for(OpenResult  r:todayOpens){
						OpenResult saved=savedResult.get(r.getDateIndex());
						if(saved==null){
							System.out.println("add new open result ,data index is :"+r.getDateIndex());
							dao.insertOpenResult(r);
						}
					}
					Map<Integer,Float> allStatistic=tool.allDataStatistics();
					Map<Integer,Float> todayStatistic=tool.statisticsBeforToday(0);
					printStatistic(allStatistic,todayStatistic,"today");
					System.out.println(" |   |\n |   |\n |   |\n\\/   \\/");
					Map<Integer,Float> befor1=tool.statisticsBeforToday(1);
					printStatistic(allStatistic,befor1,"latest 2 days ");
					System.out.println(" |   |\n |   |\n |   |\n\\/   \\/");
					Map<Integer,Float> befor2=tool.statisticsBeforToday(2);
					printStatistic(allStatistic,befor2,"latest 3 days ");
					Thread.sleep(5*60*1000);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void printStatistic(Map<Integer,Float> allStatistic,Map<Integer,Float> statistic,String day){
		System.out.println("-----------------"+day+" begin---------------");
		Map<Integer,String> result=new TreeMap<Integer, String>();
		for(Integer key:allStatistic.keySet()){
			float globalPercent=allStatistic.get(key);
			Float todayPercent=statistic.get(key);
			if(todayPercent==null){
				result.put(key, "("+globalPercent+"-0)==>"+(globalPercent*100)+"%");
			}else{
				result.put(key, "("+globalPercent+"-"+todayPercent+")==>"+((globalPercent-todayPercent)*100)+"%");
			}
		}
		for(Integer key:result.keySet()){
			String str=result.get(key);
			System.out.println(key+"------->"+str);
		}
		
		System.out.println("-----------------"+day+" end ---------------");
	}
	
	
}
