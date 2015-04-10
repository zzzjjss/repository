package com.uf.stock.alarm;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class MySchedule {
	public void startSchedule(){
		try {
			Scheduler scheduler=StdSchedulerFactory.getDefaultScheduler();
			Trigger trigger = newTrigger()  
				       .withIdentity("tradeDay", "group1")  
				       .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * 9-14 ? * MON-FRI"))  
				       .build();       
			
			JobDetail  job=newJob(GetStockCurrrentPriceJob.class).withIdentity("getStockPrice", "jobGroup").build();
			
			scheduler.scheduleJob(job, trigger);
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}
