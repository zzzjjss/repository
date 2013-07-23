package com.uf.fanfan.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	//��ȡ�������һ�죨������������  ����Sat Jul 27 23:59:59 CST 2013
	public static Date getSaturdayDate(){
		int dayOfWeek=Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		GregorianCalendar calen=new GregorianCalendar();
		calen.add(Calendar.DATE, 7-dayOfWeek);
		calen.set(Calendar.HOUR_OF_DAY, 23);
		calen.set(Calendar.MINUTE, 59);
		calen.set(Calendar.SECOND, 59);
		Date date=calen.getTime();
		System.out.println(date);
		return date;
	}
	//��ȡ���ܵ�һ�죨���գ������� ���� Sat Jul 20 00:00:00 CST 2013
	public static Date getSundayDate(){
		int dayOfWeek=Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		GregorianCalendar calen=new GregorianCalendar();
		calen.add(Calendar.DATE, 0-dayOfWeek);
		calen.set(Calendar.HOUR_OF_DAY, 00);
		calen.set(Calendar.MINUTE, 00);
		calen.set(Calendar.SECOND, 00);
		Date date=calen.getTime();
		System.out.println(date);
		return null;
	}
	
	public static void main(String[] args) {
		getSaturdayDate();
		getSundayDate();
	}
}
