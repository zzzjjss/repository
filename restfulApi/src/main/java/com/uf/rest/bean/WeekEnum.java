package com.uf.rest.bean;

public enum WeekEnum {
	SUNDAY("ζζε€?"),MONDAY("ζζδΈ?"),TUESDAY("ζζδΊ?"),WEDNESDAY("ζζδΈ?"),THURSDAY("ζζε?"),FRIDAY("ζζδΊ?"),SATURDAY("ζζε?") ;
	private String name;
	private WeekEnum(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
}
