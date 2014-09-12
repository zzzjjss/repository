package com.quick3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.management.AttributeChangeNotification;
import javax.management.Notification;
import javax.management.NotificationListener;

import com.quick3.bean.StatisticResult;
import com.quick3.mail.MailSender;

public class MBeanNotificationListener implements NotificationListener{

	@Override
	public void handleNotification(Notification notification, Object handback) {
			StringBuilder openResult=new StringBuilder();
			if(notification instanceof AttributeChangeNotification){
				AttributeChangeNotification noti=(AttributeChangeNotification)notification;
				List<OpenResult> newOpens =(List<OpenResult>)noti.getNewValue();	
				for(OpenResult open:newOpens){
					openResult.append(open.getDateIndex()+":"+open.getResult()+"\n");
					System.out.println(open.getDateIndex()+":"+open.getResult());
				}
			}
			StatisticsTool tool=new StatisticsTool();
			Map<Integer,Float> allStatistic=tool.allDataStatistics();
			Map<Integer,Float> theory=tool.theoryStatistics();
			
			
			
			StringBuilder mailInfo=new StringBuilder();
			
			
			
			
			Map<Integer,Float> todayStatistic=tool.statisticsBeforToday(0);
			Map<Integer,Float> last2days=tool.statisticsBeforToday(1);
			Map<Integer,Float> last3days=tool.statisticsBeforToday(2);
			Map<Integer,StatisticResult> result0=tool.getStatistic(allStatistic,todayStatistic,0);
			Map<Integer,StatisticResult> result1=tool.getStatistic(allStatistic,last2days,1);
			Map<Integer,StatisticResult> result2=tool.getStatistic(allStatistic,last3days,2);
			
			
			Map<Integer,StatisticResult> result0_theory=tool.getStatistic(theory,todayStatistic,0);
			Map<Integer,StatisticResult> result1_theory=tool.getStatistic(theory,last2days,1);
			Map<Integer,StatisticResult> result2_theory=tool.getStatistic(theory,last3days,2);
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
			
			if(!mailInfo.toString().trim().equals("")){
				MailSender sender=new MailSender();
				sender.sendTextMail(openResult.toString()+mailInfo.toString());
			}
			
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
	
	
}
