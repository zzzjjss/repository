package com.uf.fanfan.common;

public enum WeekEnum {
	SUNDAY("������"),MONDAY("����һ"),TUESDAY("���ڶ�"),WEDNESDAY("������"),THURSDAY("������"),FRIDAY("������"),SATURDAY("������") ;
	private String name;
	private WeekEnum(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
}
