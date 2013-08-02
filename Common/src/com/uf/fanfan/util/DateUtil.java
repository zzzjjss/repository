package com.uf.fanfan.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.uf.fanfan.common.WeekEnum;

public class DateUtil {
	//获取本周最后一天（周六）的日期  ，如Sat Jul 27 23:59:59 CST 2013
	public static Date getThisWeekSaturdayDate(){
		int dayOfWeek=Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		GregorianCalendar calen=new GregorianCalendar(Locale.CHINA);
		calen.add(Calendar.DATE, 7-dayOfWeek);
		calen.set(Calendar.HOUR_OF_DAY, 23);
		calen.set(Calendar.MINUTE, 59);
		calen.set(Calendar.SECOND, 59);
		Date date=calen.getTime();
		System.out.println(date);
		return date;
	}
	//获取本周第一天（周日）的日期 ，如 Sat Jul 20 00:00:00 CST 2013
	public static Date getThisWeekSundayDate(){
		int dayOfWeek=Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		GregorianCalendar calen=new GregorianCalendar(Locale.CHINA);
		calen.add(Calendar.DATE, 1-dayOfWeek);
		calen.set(Calendar.HOUR_OF_DAY, 00);
		calen.set(Calendar.MINUTE, 00);
		calen.set(Calendar.SECOND, 00);
		Date date=calen.getTime();
		System.out.println(date);
		return null;
	}
	public static WeekEnum getWeekdayByDate(Date date){
		GregorianCalendar calen=new GregorianCalendar(Locale.CHINA);
		calen.setTime(date);
		int dayOfWeek=calen.get(Calendar.DAY_OF_WEEK);
		switch(dayOfWeek){
			case Calendar.SUNDAY:return WeekEnum.SUNDAY;
			case Calendar.MONDAY:return WeekEnum.MONDAY;
			case Calendar.TUESDAY:return WeekEnum.TUESDAY;
			case Calendar.THURSDAY:return WeekEnum.THURSDAY;
			case Calendar.WEDNESDAY:return WeekEnum.WEDNESDAY;
			case Calendar.FRIDAY:return WeekEnum.FRIDAY;
			case Calendar.SATURDAY:return WeekEnum.SATURDAY;
		}
		return null;
		
	}
	
	public static void main(String[] args) {
		getThisWeekSaturdayDate();
		getThisWeekSundayDate();
		System.out.println(getWeekdayByDate(new Date(System.currentTimeMillis())).getName());
	}
}
