package com.quick3;

import java.util.Map;
import java.util.TreeMap;

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
	
}
