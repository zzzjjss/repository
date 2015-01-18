package com.uf.rest.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	public static void writeContentToFile(byte conetent[],String filePath){
		File file=new File(filePath);
		FileOutputStream out;
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			out = new FileOutputStream(file);
			out.write(conetent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
