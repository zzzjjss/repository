package com.uf.stock.alarm.desktop;

import java.util.List;

import com.uf.stock.alarm.Siren;

public class CmdAlarm implements Siren{

  public void alarm(List<String> infos) {
    if(infos!=null&&!infos.isEmpty()){
      System.out.println("******************begin**********************");
      for(String info:infos){
        System.out.println(info);
      }
      System.out.println("******************end**********************");
    }
    
    
  }

}
