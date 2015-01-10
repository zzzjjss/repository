package com.uf.rest.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {
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
	
	public static Date getDateBegin(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
		
	}
	public static Date getDateEnd(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}
	public static void main(String[] args) {
	
	}
}
