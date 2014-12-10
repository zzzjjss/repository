package com.uf.stockshow.unit;

public class StringUtil {
	public static boolean isNullOrEmpty(String str){
		if(str==null||str.trim().equals(""))
			return true;
		return false;
	}
}
