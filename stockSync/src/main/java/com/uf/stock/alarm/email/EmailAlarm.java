package com.uf.stock.alarm.email;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.uf.stock.alarm.Siren;

public class EmailAlarm implements Siren{

  public void alarm(List<String> infos) {
    String content=StringUtils.join(infos, "\r\n");
    MailSender sender=new MailSender();
    sender.sendTextMail(content);
  }

}
