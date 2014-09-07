package com.quick3;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.quick3.bean.StatisticResult;

public class StatisticsTool {
	private DataDao dao=new DataDao();
	public Map<Integer,Float> allDataStatistics(){
		Map<Integer,Float>  statistics=new TreeMap<Integer, Float>();
		Map<Integer,Integer> numberCount=dao.groupAll();
		int allNumber=0;
		for(Integer key:numberCount.keySet()){
			allNumber+=numberCount.get(key);
		}
		for(Integer key:numberCount.keySet()){
			float percent=((float)numberCount.get(key))/((float)allNumber);
			statistics.put(key, percent);
		}
		return statistics;
		
	}
	public Map<Integer,Float> statisticsBeforToday(int days){
		Map<Integer,Float>  statistics=new TreeMap<Integer, Float>();
		Map<Integer,Integer> numberCount=dao.groupDataBeforeToday(days);
		int allNumber=0;
		for(Integer key:numberCount.keySet()){
			allNumber+=numberCount.get(key);
		}
		for(Integer key:numberCount.keySet()){
			float percent=((float)numberCount.get(key))/((float)allNumber);
			statistics.put(key, percent);
		}
		return statistics;
	}
	public Map<Integer,StatisticResult> getStatistic(Map<Integer,Float> allStatistic,Map<Integer,Float> statistic,int day){
		Map<Integer,StatisticResult> returnRes=new HashMap<Integer, StatisticResult>();
		Map<Integer,Float> resultValue=new TreeMap<Integer, Float>();
		Float  subValue=0.00000f;
		Float validTotal=1f;
		for(Integer key:allStatistic.keySet()){
			StatisticResult sta=new StatisticResult();
			sta.setDays(day);
			sta.setOpenResult(key);
			float globalPercent=allStatistic.get(key);
			sta.setGlobalPercent(globalPercent);
			Float todayPercent=statistic.get(key);
			returnRes.put(key, sta);
			if(todayPercent==null){
				resultValue.put(key, globalPercent);
				sta.setDaysPercent(0.0000f);
			}else{
				sta.setDaysPercent(todayPercent);
				float  tmp=globalPercent-todayPercent;
				if(tmp<0){
					subValue+=globalPercent;
					validTotal-=globalPercent;
				}else{
					subValue+=tmp;
					validTotal-=todayPercent;
				}
				
				resultValue.put(key, tmp);
			}
		}
		
		Map<Integer,Float> percentResult=new TreeMap<Integer, Float>();
		for(Integer key:resultValue.keySet()){
			StatisticResult statis=returnRes.get(key);
			
			Float tmp=resultValue.get(key);
			if(tmp<0){
				percentResult.put(key, 0f);
				statis.setNextOpenPercent(0f);
			}else{
				Float a=allStatistic.get(key);
				Float b=(a/validTotal)*100*subValue;
				float nextOpenPercent=tmp+b;
				statis.setNextOpenPercent(nextOpenPercent);
				percentResult.put(key, nextOpenPercent);
				
			}
		}
		
		return returnRes;
	}
}
