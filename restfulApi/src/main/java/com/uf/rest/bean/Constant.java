package com.uf.rest.bean;

public class Constant {
	public static final String SYSTEM_EXCEPTION_CODE="10001";
	
	public static final String USER_EXIST_CODE="20001";
	public static final String USER_NOT_EXIST_CODE="20002";
	public static final String PST_WRONG_CODE="20003";
	public static final String OLD_PST_WRONG_CODE="20004";
	public static final String USER_NOT_LOGIN_CODE="20005";
	public static final String SHOP_NOT_EXIST="20006";
	public static final String VALUE_NOT_EXIST="20007";
	public static final String GOOD_NOT_EXIST="20008";
	
	
	
	public static final Integer ORDER_STATE_CANCELED=0;
	public static final Integer ORDER_STATE_WAITGET=1;
	public static final Integer ORDER_STATE_PROCESSING=2;
	public static final Integer ORDER_STATE_WAITSENT=3;
	public static final Integer ORDER_STATE_COMPLETE=4;
	public static final Integer ORDER_STATE_PAYED=64;
	
	public static final String TEMP_PATH=System.getProperty("user.home")+"/";
}
