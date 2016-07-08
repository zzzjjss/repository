package com.uf.ask.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uf.ask.service.WechatService;

/**
 * Servlet implementation class WeChatServlet
 */
public class WeChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String TOKEN="jasonzhang";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeChatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String echostr=request.getParameter("echostr");
	  String nonce=request.getParameter("nonce");
	  String timestamp=request.getParameter("timestamp");
	  String signature=request.getParameter("signature");
	  String responseStr="";
	  if(checkSignature(nonce,timestamp,signature)){
	    responseStr=echostr;
	  }
	  response.getOutputStream().write(responseStr.getBytes());
	  response.getOutputStream().flush();
	}
	private boolean checkSignature(String randomStr,String timestamp,String signature){
      return true;
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setCharacterEncoding("UTF-8");  
      response.setCharacterEncoding("UTF-8");  

      StringBuffer sb = new StringBuffer();  
      InputStream is = request.getInputStream();  
      InputStreamReader isr = new InputStreamReader(is, "UTF-8");  
      BufferedReader br = new BufferedReader(isr);  
      String s = "";  
      while ((s = br.readLine()) != null) {  
          sb.append(s);  
      }  
      String xml = sb.toString(); 
      System.out.println("receive:-->"+xml);
      OutputStream out=response.getOutputStream();
      new WechatService().processWechatMsgToOutputStrem(xml,out);  
      out.flush();
	}

}
