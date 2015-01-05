package com.uf.rest.util;


public class FileUtil {
	public static String getFileExtendName(String  fileName){
		int index=fileName.lastIndexOf(".");
		if(index==-1)
			return "";
		return fileName.substring(index);
	}
}
