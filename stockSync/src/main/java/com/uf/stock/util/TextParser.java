package com.uf.stock.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import com.uf.stock.bean.AlarmStock;
import com.uf.stock.dao.AlarmStockDao;
import com.uf.stock.dao.DaoFactory;

public class TextParser {
  public static void main(String[] args) {
    final AlarmStockDao dao=DaoFactory.getDao(AlarmStockDao.class);
    try{
      Files.readLines(new File("C:\\jason\\temp\\alarm.txt"),Charset.forName("gb2312") ,new LineProcessor<Boolean>() {

        public boolean processLine(String line) throws IOException {
          if(line.length()>6){
            String info[]=StringUtils.split(line);
            if(StringUtils.isNumeric(info[0].trim())){
               AlarmStock stock=dao.findByStockCode(Integer.parseInt(info[0])); 
               if(stock==null){
                 stock=new AlarmStock();
                 stock.setStockCode(Integer.parseInt(info[0]));
                 stock.setStockName(info[1]);
               }
               stock.setAlarmBuyPrice(Float.parseFloat(info[5]));
               dao.saveOrUpdate(stock);
               System.out.println(info[0]);
            }
          }
          return true;
        }

        public Boolean getResult() {
          // TODO Auto-generated method stub
          return null;
        }
        
      });
    }catch(Exception e){
      e.printStackTrace();
    }
    
  }
}
