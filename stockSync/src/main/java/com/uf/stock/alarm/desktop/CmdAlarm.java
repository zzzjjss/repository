package com.uf.stock.alarm.desktop;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import com.google.common.io.Files;
import com.uf.stock.alarm.Siren;

public class CmdAlarm implements Siren{

  public void alarm(List<String> infos) {
    try{
      String content=StringUtils.join(infos, "\r\n");
      File file=new File(SystemUtils.getJavaIoTmpDir(),"alarm");
      Files.write(content.getBytes(),file );
      Process  p=Runtime.getRuntime().exec("notepad "+file.getAbsolutePath());
      p.waitFor();
      System.out.println("close ok");
    }catch(Exception e){
      e.printStackTrace();
    }
    
    
  }

  public static void main(String[] args) {
    String con[]=new String[]{"aaa","bbb"};
    new CmdAlarm().alarm(Arrays.asList(con));
  }
}
