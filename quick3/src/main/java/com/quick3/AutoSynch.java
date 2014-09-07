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
		Map<Integer,Float> resultValue=new TreeMap<Integer, Float>();
		Float  subValue=0.00000f;
		Float validTotal=0.00000f;
		for(Integer key:allStatistic.keySet()){
			float globalPercent=allStatistic.get(key);
			Float todayPercent=statistic.get(key);
			if(todayPercent==null){
				resultValue.put(key, globalPercent);
				result.put(key, "("+globalPercent+"-0)==>"+(globalPercent*100)+"%");
			}else{
				float  tmp=globalPercent-todayPercent;
				if(tmp<0){
					subValue+=todayPercent;
				}else{
					subValue+=tmp;
					validTotal+=globalPercent;
				}
				result.put(key, "("+globalPercent*100+"-"+todayPercent*100+")==>"+(tmp*100)+"%");
				resultValue.put(key, tmp);
			}
		}
		
		Map<Integer,Float> percentResult=new TreeMap<Integer, Float>();
		for(Integer key:resultValue.keySet()){
			Float tmp=resultValue.get(key);
			if(tmp<0){
				percentResult.put(key, 0f);
			}else{
				Float a=allStatistic.get(key);
				Float b=(a/validTotal)*100*subValue;
				percentResult.put(key, tmp+b);
						
			}
		}
		
		
		
		for(Integer key:result.keySet()){
			String str=result.get(key);
			System.out.println(key+"------->"+str+" --------->"+percentResult.get(key)+"%");
		}
		
		
		
		System.out.println("-----------------"+day+" end ---------------");
	}
	
	
}
