package com.uf.fanfan.common;

public enum WeekEnum {
	SUNDAY("星期天"),MONDAY("星期一"),TUESDAY("星期二"),WEDNESDAY("星期三"),THURSDAY("星期四"),FRIDAY("星期五"),SATURDAY("星期六") ;
	private String name;
	private WeekEnum(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
}
