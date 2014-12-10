package com.quick3.statistic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.quick3.Constant;
import com.quick3.DataDao;
import com.quick3.OpenResult;
import com.quick3.bean.StatisticResult;

public class RateStatistic {
	private DataDao dao=new DataDao();
	
	public String parseNewOpenResults(List<OpenResult> newOpens){
		Map<Integer,Float> allStatistic=allDataStatistics();
		Map<Integer,Float> theory=theoryStatistics();
		StringBuilder mailInfo=new StringBuilder();
		Map<Integer,Float> todayStatistic=statisticsBeforToday(0);
		Map<Integer,Float> last2days=statisticsBeforToday(1);
		Map<Integer,Float> last3days=statisticsBeforToday(2);
		Map<Integer,StatisticResult> result0=getStatistic(allStatistic,todayStatistic,0);
		Map<Integer,StatisticResult> result1=getStatistic(allStatistic,last2days,1);
		Map<Integer,StatisticResult> result2=getStatistic(allStatistic,last3days,2);
		
		
		Map<Integer,StatisticResult> result0_theory=getStatistic(theory,todayStatistic,0);
		Map<Integer,StatisticResult> result1_theory=getStatistic(theory,last2days,1);
		Map<Integer,StatisticResult> result2_theory=getStatistic(theory,last3days,2);
		printInfo(result0_theory, 0);
		printInfo(result1_theory, 1);
		printInfo(result2_theory, 2);
		
		
		
		List<StatisticResult> out0=printAndGetImportInfo(result0,0);
		if(out0.size()>0){
			for(StatisticResult s:out0){
				String outInfo=getPrintInfo(s);
				Integer mostTime=Constant.RESULT_WIN.get(s.getOpenResult());
				mailInfo.append("(before today 0)--->"+outInfo+"( mostTime-->"+mostTime+"\n");
				StatisticResult s2=result1.get(s.getOpenResult());
				String outInfo2=getPrintInfo(s2);
				mailInfo.append("(before today 1)--->"+outInfo2+"( mostTime-->"+mostTime+"\n");
				
				StatisticResult s3=result2.get(s.getOpenResult());
				String outInfo3=getPrintInfo(s3);
				mailInfo.append("(before today 2)--->"+outInfo3+"( mostTime-->"+mostTime+"\n");
			}
		}
		
		
		List<StatisticResult> out1=printAndGetImportInfo(result1,1);
		if(out1.size()>0){
			for(StatisticResult s:out1){
				String outInfo=getPrintInfo(s);
				Integer mostTime=Constant.RESULT_WIN.get(s.getOpenResult());
				mailInfo.append("(before today 1)--->"+outInfo+"( mostTime-->"+mostTime+"\n");
				
				StatisticResult s0=result0.get(s.getOpenResult());
				String outInfo0=getPrintInfo(s0);
				mailInfo.append("(before today 0)--->"+outInfo0+"( mostTime-->"+mostTime+"\n");
				
				StatisticResult s2=result2.get(s.getOpenResult());
				String outInfo2=getPrintInfo(s2);
				mailInfo.append("(before today 2)--->"+outInfo2+"( mostTime-->"+mostTime+"\n");
			}
		}
		
		
		List<StatisticResult> out2=printAndGetImportInfo(result2,2);
		if(out2.size()>0){
			for(StatisticResult s:out2){
				String outInfo=getPrintInfo(s);
				Integer mostTime=Constant.RESULT_WIN.get(s.getOpenResult());
				mailInfo.append("(before today 2)--->"+outInfo+"( mostTime-->"+mostTime+"\n");
				
				StatisticResult s0=result0.get(s.getOpenResult());
				String outInfo0=getPrintInfo(s0);
				mailInfo.append("(before today 0)--->"+outInfo0+"( mostTime-->"+mostTime+"\n");
				
				StatisticResult s1=result1.get(s.getOpenResult());
				String outInfo1=getPrintInfo(s1);
				mailInfo.append("(before today 1)--->"+outInfo1+"( mostTime-->"+mostTime+"\n");
			}
		}
		return mailInfo.toString();
	}
	
	public void printInfo(Map<Integer,StatisticResult> result,int daysBeforeToday){
		System.out.println("Theory ----------------------before today "+daysBeforeToday+"   begin--------------------");
		List<StatisticResult> orderResult=getOrderedStatisticResult(result);
		for(StatisticResult statistic:orderResult){
			float  global=statistic.getGlobalPercent();
			float  daysPercent=statistic.getDaysPercent();
			float sub=global-daysPercent;
			int openReult=statistic.getOpenResult();
			float nextPercent=statistic.getNextOpenPercent();
			String outInfo=openReult+"---->("+global*100+"%-"+daysPercent*100+"% ="+sub*100+"% )--------->"+nextPercent*100+"%";
			System.out.println(outInfo);
		}
		System.out.println("Theory -------before today "+daysBeforeToday+" end--------------------");
	}
	private String getPrintInfo(StatisticResult s){
		int open=s.getOpenResult();
		float global=s.getGlobalPercent();
		float daysPercent=s.getDaysPercent();
		float sub=global-daysPercent;
		float nextPercent=s.getNextOpenPercent();
		return open+"---->("+global*100+"%-"+daysPercent*100+"% ="+sub*100+"% )--------->"+nextPercent*100+"%";
	}

	public List<StatisticResult> printAndGetImportInfo(Map<Integer,StatisticResult> result,int daysBeforeToday){
		List<StatisticResult> importInfo=new ArrayList<StatisticResult>();
		System.out.println("----------------------before today "+daysBeforeToday+"   begin--------------------");
		List<StatisticResult> orderResult=getOrderedStatisticResult(result);
		for(StatisticResult statistic:orderResult){
			float  global=statistic.getGlobalPercent();
			float  daysPercent=statistic.getDaysPercent();
			float sub=global-daysPercent;
			int openReult=statistic.getOpenResult();
			float nextPercent=statistic.getNextOpenPercent();
			String outInfo=openReult+"---->("+global*100+"%-"+daysPercent*100+"% ="+sub*100+"% )--------->"+nextPercent*100+"%";
			if((nextPercent>=0.4&&(openReult==10||openReult==11||openReult==9||openReult==12))||
					(nextPercent>=0.39&&(openReult==8||openReult==13))||
					(nextPercent>=0.36&&(openReult==7||openReult==14))||
					(nextPercent>=0.34&&(openReult==6||openReult==15))||
					(nextPercent>=0.32&&(openReult==5||openReult==16))||
					(nextPercent>=0.28&&(openReult==4||openReult==17))||
					(nextPercent>=0.20&&(openReult==3||openReult==18)) ){
				
				importInfo.add(statistic);
			}
			System.out.println(outInfo);
		}
		System.out.println("----------------------before today "+daysBeforeToday+" end--------------------");
		return importInfo;
	}
	
	private List<StatisticResult> getOrderedStatisticResult(Map<Integer,StatisticResult> statistics){
		LinkedList<StatisticResult> result=new LinkedList<StatisticResult>();
		for(Integer key:statistics.keySet()){
			StatisticResult  s=statistics.get(key);
			if(result.size()==0){
				result.add(s);
			}else{
				for(int i=0;i<result.size();i++){
					StatisticResult before=result.get(i);
					StatisticResult first=result.getFirst();
					StatisticResult last=result.getLast();
					if(s.getNextOpenPercent()>=first.getNextOpenPercent()){
						result.addFirst(s);
						break;
					}
					if(s.getNextOpenPercent()<=last.getNextOpenPercent()){
						result.addLast(s);
						break;
					}
					StatisticResult after=result.get(i+1);
					if(before.getNextOpenPercent()>=s.getNextOpenPercent()&&after.getNextOpenPercent()<=s.getNextOpenPercent()){
						result.add(i+1, s);
						break;
					}
					
				}
			}
		}
		return result;
	}
	
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
