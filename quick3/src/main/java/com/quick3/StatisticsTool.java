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
	public Map<Integer,Float> theoryStatistics(){
		Map<Integer,Float>  result=new HashMap<Integer,Float>();
		Map<String,Integer>  allCount=new HashMap<String, Integer>();
		for(int i=1;i<=6;i++){
			for(int j=1;j<=6;j++){
				for(int k=1;k<=6;k++){
					String sum=String.valueOf(i+j+k);
					Integer count=allCount.get(sum);
					if(count==null){
						allCount.put(sum, 1);
					}else{
						allCount.put(sum, count+1);
					}
				}
			}
		}
		
		int  all=6*6*6;
		for(String key:allCount.keySet()){
			Integer value=allCount.get(key);
			float percent=((float)value)/(float)all;
			result.put(Integer.valueOf(key), percent);
		}
		return result;
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
				todayPercent=0.00f;
			}
			sta.setDaysPercent(todayPercent);
			float  tmp=globalPercent-todayPercent;
			resultValue.put(key, tmp);
			if(tmp<=0){
				validTotal-=globalPercent;
			}else{
				validTotal-=todayPercent;
			}
		}
		
		for(Integer key:resultValue.keySet()){
			StatisticResult statis=returnRes.get(key);
			
			Float tmp=resultValue.get(key);
			if(tmp<=0){
				statis.setNextOpenPercent(0f);
			}else{
				Float b=(tmp/validTotal)*(1-validTotal);
				float nextOpenPercent=tmp+b;
				statis.setNextOpenPercent(nextOpenPercent);
			}
		}
		
		return returnRes;
	}
}
