package com.uf.rest.util;

import java.io.File;
import java.io.IOException;


public class FileUtil {
	public static String getFileExtendName(String  fileName){
		int index=fileName.lastIndexOf(".");
		if(index==-1)
			return "";
		return fileName.substring(index);
	}
	public static byte[] getFileContent(String filePath){
		File file=new File(filePath);
		if(file.exists()){
			try {
				return org.aspectj.util.FileUtil.readAsByteArray(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
