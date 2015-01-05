package com.uf.rest.bean;

public enum WeekEnum {
	SUNDAY("星期�?"),MONDAY("星期�?"),TUESDAY("星期�?"),WEDNESDAY("星期�?"),THURSDAY("星期�?"),FRIDAY("星期�?"),SATURDAY("星期�?") ;
	private String name;
	private WeekEnum(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
}
