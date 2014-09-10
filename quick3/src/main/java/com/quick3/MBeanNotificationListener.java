package com.quick3;

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
			if(notification instanceof AttributeChangeNotification){
				AttributeChangeNotification noti=(AttributeChangeNotification)notification;
				List<OpenResult> newOpens =(List<OpenResult>)noti.getNewValue();	
				for(OpenResult open:newOpens){
					System.out.println(open.getDateIndex()+":"+open.getResult());
				}
			}
			StatisticsTool tool=new StatisticsTool();
			Map<Integer,Float> allStatistic=tool.allDataStatistics();
			StringBuilder mailInfo=new StringBuilder();
			
			Map<Integer,Float> todayStatistic=tool.statisticsBeforToday(0);
			Map<Integer,StatisticResult> result0=tool.getStatistic(allStatistic,todayStatistic,0);
			String out0=printAndGetImportInfo(result0,0);
			mailInfo.append(out0);
			
			Map<Integer,Float> last2days=tool.statisticsBeforToday(1);
			Map<Integer,StatisticResult> result1=tool.getStatistic(allStatistic,last2days,1);
			String out1=printAndGetImportInfo(result1,1);
			mailInfo.append(out1);
			
			Map<Integer,Float> last3days=tool.statisticsBeforToday(2);
			Map<Integer,StatisticResult> result2=tool.getStatistic(allStatistic,last3days,2);
			String out2=printAndGetImportInfo(result2,2);
			mailInfo.append(out2);
			
			if(!mailInfo.toString().trim().equals("")){
				MailSender sender=new MailSender();
				sender.sendTextMail(mailInfo.toString());
			}
			
	}

	public String printAndGetImportInfo(Map<Integer,StatisticResult> result,int daysBeforeToday){
		StringBuilder importInfo=new StringBuilder();
		System.out.println("----------------------before today "+daysBeforeToday+"   begin--------------------");
		List<StatisticResult> orderResult=getOrderedStatisticResult(result);
		for(StatisticResult statistic:orderResult){
			float  global=statistic.getGlobalPercent();
			float  daysPercent=statistic.getDaysPercent();
			float sub=global-daysPercent;
			float nextPercent=statistic.getNextOpenPercent();
			String outInfo=statistic.getOpenResult()+"---->("+global*100+"%-"+daysPercent*100+"% ="+sub*100+"% )--------->"+nextPercent*100+"%";
			if(nextPercent>=0.3){
				importInfo.append("(before today"+daysBeforeToday+")--->"+outInfo+"\n");
			}
			System.out.println(outInfo);
		}
		System.out.println("----------------------before today "+daysBeforeToday+" end--------------------");
		return importInfo.toString();
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
