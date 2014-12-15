package com.quick3.statistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.quick3.DataDao;
import com.quick3.OpenResult;

public class StepStatistic {
	private  Map<Integer,Map<Integer,Integer>> allNumberStepStatistic=new TreeMap<Integer, Map<Integer,Integer>>();
	private DataDao dao=new DataDao(); 
	public StepStatistic(){
		for(int i=3;i<=18;i++){
			Map<Integer,Integer> statistic=dao.stepStatistic(i);
			allNumberStepStatistic.put(i, statistic);
		}
	}
	
	public String parseNewOpenResults(List<OpenResult> newOpens){
		StringBuilder resultInfo=new StringBuilder();
		for(OpenResult open:newOpens){
			int openNumber=open.getResult();
			Map<Integer,Integer> statistic=dao.stepStatistic(openNumber);
			allNumberStepStatistic.put(openNumber, statistic);
			System.out.println("step statistic-->"+openNumber);
		}
		Map<Integer,Integer> stepSum=new HashMap<Integer, Integer>();
		for(Integer key:allNumberStepStatistic.keySet()){
			Map<Integer, Integer> numbemStepStatistic=allNumberStepStatistic.get(key);
			int sum=0;
			for(Integer step:numbemStepStatistic.keySet()){
				sum=sum+numbemStepStatistic.get(step);
			}
			stepSum.put(key, sum);
		}
		Map<Integer,Map<Integer,Double>> allNumberStepRate=new TreeMap<Integer, Map<Integer,Double>>();
		for(Integer key:allNumberStepStatistic.keySet()){
			Map<Integer, Integer> numbemStepStatistic=allNumberStepStatistic.get(key);
			Integer sumStep=stepSum.get(key);
			Map<Integer,Double> rateMap=new TreeMap<Integer, Double>();
			for(Integer step:numbemStepStatistic.keySet()){
				int stepNumber=numbemStepStatistic.get(step);
				double ration=(double)(((double)stepNumber/(double)sumStep)*100.00d);
				rateMap.put(step,ration);
			}
			allNumberStepRate.put(key, rateMap);
		}
		
		
		for(int i=3;i<=18;i++){
			int nextOpenStep=dao.findLatestOpenResultToNextStep(i);
			Map<Integer,Double> stepRate=allNumberStepRate.get(i);
			double rateSum=0.0d;
			for(Integer step:stepRate.keySet()){
				if(step<=nextOpenStep){
					Double rate=stepRate.get(step);
					rateSum=rateSum+rate;
				}
			}
			String info=i+"---->nextOpenStep->"+nextOpenStep+"  nextOpenRate----> "+rateSum;
			System.out.println(info);
			if(rateSum>99){
				resultInfo.append(info+"\n");
			}
		}
		
		return resultInfo.toString();
	}
	
}
