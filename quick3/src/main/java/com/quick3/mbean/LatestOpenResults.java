package com.quick3.mbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.management.AttributeChangeNotification;
import javax.management.NotificationBroadcasterSupport;

import com.quick3.OpenResult;
import com.quick3.StatisticsTool;
import com.quick3.bean.StatisticResult;

public class LatestOpenResults extends NotificationBroadcasterSupport implements LatestOpenResultsMBean{
	private List<OpenResult> latestOpenResults=new ArrayList<OpenResult>();
	private AtomicLong sequenceNumber = new AtomicLong(1);   
	public List<OpenResult> getLatestOpenResults() {
		return latestOpenResults;
	}


	public void setLatestOpenResults(List<OpenResult> latestOpenResults) {
		AttributeChangeNotification notification=new AttributeChangeNotification(this, sequenceNumber.getAndIncrement(),  System.currentTimeMillis(),   
                AttributeChangeNotification.ATTRIBUTE_CHANGE, "new open result", "java.util.List",  this.latestOpenResults, latestOpenResults); 
		super.sendNotification(notification);
		this.latestOpenResults = latestOpenResults;
	}
	
}
