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
		if(filePath!=null){
			File file=new File(filePath);
			if(file.exists()){
				try {
					return org.aspectj.util.FileUtil.readAsByteArray(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	public static String writeContentToFile(byte conetent[],String dir,String fileName){
		File dirPath=new File(dir);
		try {
			dirPath.mkdirs();
			File file=new File(dirPath+"/"+fileName);
			FileOutputStream out = new FileOutputStream(file);
			out.write(conetent);
			out.close();
			return file.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
