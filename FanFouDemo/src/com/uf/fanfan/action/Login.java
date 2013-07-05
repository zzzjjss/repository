package com.uf.fanfan.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.uf.fanfan.service.LoginService;

public class Login extends BaseAction{
	LoginService loginService=(LoginService)appContext.getBean("loginService");
	Logger log = LoggerFactory.getLogger(Login.class);
	final int noVerifycodeLoginTime=5;
	
	private String userName;
	private String password;
	private String userType;
	private String verifyCode;
	
	
	public String login(){
		try{
			String remoteIp=request.getRemoteAddr();
			Map<String,Long[]> loginRecord=(Map<String, Long[]>)request.getServletContext().getAttribute("remoteAddr");
			//preLoginTime[0] is the logintime ,preLoginTime[1] is the login 次数
			Long preLoginTime[]=loginRecord.get(remoteIp);
			if(preLoginTime==null){
				preLoginTime=new Long[2];
				preLoginTime[0]=System.currentTimeMillis();
				preLoginTime[1]=1L;
				loginRecord.put(remoteIp, preLoginTime);
			}else{
				long interval=System.currentTimeMillis()-preLoginTime[0];
				preLoginTime[1]=preLoginTime[1].longValue()+1;
				if(interval<3600&&preLoginTime[1]>noVerifycodeLoginTime){
					if(!verifyCode.equalsIgnoreCase((String)session.getAttribute("verifyCode"))){
						writeResultToClient("text/paint", "verifyCodeError");
						return null;
					}
				}
			}
			boolean res=loginService.login(userName, password, userType);
			if(res)
				this.writeResultToClient("text/plain", "success");
			else{
				if(preLoginTime[1]>=noVerifycodeLoginTime)
					writeResultToClient("text/paint", "getVerrfyCode");
				else
					writeResultToClient("text/paint", "fail");
			}
				
		}catch(Exception e){
			log.error("login error", e);
			writeResultToClient("text/paint", "error");
		}
		return null;
		
	}

	public String generateVerifyCode() {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 表明生成的响应是图片
		response.setContentType("image/jpeg");

		// 矩形的宽和高
		int width = 150, height = 40;
		// BufferedImage 子类描述具有可访问图像数据缓冲区的 Image
		// 构造一个类型为预定义图像类型之一的 BufferedImage
		// TYPE_INT_RGB:表示一个图像，它具有合成整数像素的 8 位 RGB 颜色分量
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// Graphics 类是所有图形上下文的抽象基类，允许应用程序在组件（已经在各种设备上实现）以及闭屏图像上进行绘制。
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		// 填充指定的矩形
		g.fillRect(1, 1, width - 1, height - 1);
		g.setColor(new Color(102, 102, 102));
		// 绘制指定矩形的边框
		g.drawRect(0, 0, width - 1, height - 1);
		// 将此图形上下文的字体设置为指定字体
		g.setFont(mFont);
		// 将此图形上下文的当前颜色设置为指定颜色
		g.setColor(getRandColor(160, 200));

		// 画随机线
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			// 在此图形上下文的坐标系中，使用当前颜色在点 (x1, y1) 和 (x2, y2) 之间画一条线
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 从另一方向画随机线
		for (int i = 0; i < 70; i++) {
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			int xl = random.nextInt(12) + 1;
			int yl = random.nextInt(6) + 1;
			g.drawLine(x, y, x - xl, y - yl);
		}

		// 生成随机数,并将随机数字转换为字母
		String sRand = "";
		for (int i = 0; i < 6; i++) {
			// 返回下一个伪随机数，它是此随机数生成器的序列中均匀分布的 int 值
			int itmp = random.nextInt(26) + 65;
			char ctmp = (char) itmp;
			sRand += String.valueOf(ctmp);
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// 使用此图形上下文的当前字体和颜色绘制由指定 string 给定的文本
			// 两个int参数分别是(x,y)坐标用于调整字体在矩形中的位置
			g.drawString(String.valueOf(ctmp), 15 * i + 15, 30);
		}

		// 返回与当前request对象关联的session,如果没有这个session对象并且参数为true，则返回一个新的session
		HttpSession session = request.getSession(true);
		session.setAttribute("verifyCode", sRand);

		// 释放此图形的上下文以及它使用的所有系统资源
		g.dispose();
		// getOutputStream():返回一个适合向该response对象写二进制数据的输出流
		// ImageIO.write():(static)使用支持给定格式的任意 ImageWriter 将一个图像写入 OutputStream
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//根据指定名称、样式和磅值大小，创建一个新 Font。
    //Font.BOLD:粗体样式常量
    //int参数为字体大小
    private Font mFont = new Font("Times New Roman", Font.BOLD, 26);

    Color getRandColor(int fc, int bc) {
        //创建一个随机数生成器
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	
}
