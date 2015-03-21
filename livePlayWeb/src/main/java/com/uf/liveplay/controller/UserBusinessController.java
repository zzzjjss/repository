package com.uf.liveplay.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uf.liveplay.entity.PublicMessage;
import com.uf.liveplay.entity.Teacher;
import com.uf.liveplay.entity.User;
import com.uf.liveplay.service.UserService;
import com.uf.liveplay.socketio.SocketIoServer;
import com.uf.liveplay.unit.ConfigVariable;
import com.uf.liveplay.unit.RegistFilter;
import com.uf.liveplay.unit.SessionCache;
import com.uf.liveplay.unit.UnknowUserFilter;

@Controller
public class UserBusinessController {
	@Autowired
	private UserService userService;
	@Autowired
	private ConfigVariable config;
	@Autowired
	private SocketIoServer socketIoServer;
	
	@RequestMapping("/vote")
	@ResponseBody
	public String vote(@RequestParam Map<String,String> allRequestParams,HttpServletRequest request){
		String voteItem=allRequestParams.get("vote");
		userService.vote(voteItem);
		return "ok";
	}
	@RequestMapping("/getVoteResult")
	@ResponseBody
	public String getVoteResult(){
		Map<String, String> result=userService.statisticVote();
		return JSONObject.fromObject(result).toString();
	}
	
	
	@RequestMapping("/controller/saveUserInfo")
	@ResponseBody
	public String saveUserInfo(@RequestParam Map<String,String> allRequestParams,HttpServletRequest request){
		String userId=allRequestParams.get("userId");
		String oldPwd=allRequestParams.get("oldPassword");
		String newPwd=allRequestParams.get("newPassword");
		String phone=allRequestParams.get("phone");
		try {
			User user=userService.findUserById(Integer.parseInt(userId));
			if(user==null){
				return "用户不存在！";
			}else if(!user.getPassword().equals(oldPwd)){
				return "旧密码错误！";
			}else{
				user.setPassword(newPwd);
				user.setPhone(phone);
				userService.saveUserInfo(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "ok";
	}
	@RequestMapping("/login")
	@ResponseBody
	public String login(@RequestParam Map<String,String> allRequestParams,HttpServletRequest request){
		String userName=allRequestParams.get("userName");
		String password=allRequestParams.get("password");
		User user=userService.findUserByName(userName);
//		if(user!=null&&SessionCache.findUserSessionIdByUserId(user.getId())!=null){
//			return "用户已登录。";
//		}
		try{
			if(userService.login(userName,password)){
				request.getSession().setAttribute("user", user);
				return "ok";
			}else{
				return "用户名或密码错误！";
			}
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
	}
	@RequestMapping("/controller/logout")
	@ResponseBody
	public String logout(ModelMap model,HttpServletRequest request){
		request.getSession().invalidate();
		return "ok";
	}
	@RequestMapping("/controller/isIpCanListen")
	@ResponseBody
	public String isIpCanListen(HttpServletRequest request){
		UnknowUserFilter filter=new UnknowUserFilter(config.getUnknowUserListenTimeMinute()*60, config.getUnknowUserListenIntervalHour()*60*60);
		if(filter.isIpCanListener(request.getRemoteAddr())){
			return "true";
		}else{
			return "false";
		}
	}
	@RequestMapping("/controller/keepSession")
	@ResponseBody
	public String keepSession(HttpServletRequest request){
		return "";
	}
	
	@RequestMapping("/views/popupQqRegine")
	public String popupQqReginePage(ModelMap model){
		model.addAttribute("listenMinute", config.getUnknowUserListenTimeMinute());
		return "popupQqRegine";
	}
	@RequestMapping("/views/main")
	public String mainPage(ModelMap model,HttpServletRequest request){
		Object obj=request.getSession().getAttribute("user");
		String context=request.getServletContext().getContextPath();
		model.addAttribute("context",context);
		User user=null;
		if(obj instanceof User){
			user=(User)obj;
			model.addAttribute("user",user);
			model.addAttribute("sessionId", request.getSession().getId());
		}else{
			UnknowUserFilter filter=new UnknowUserFilter(config.getUnknowUserListenTimeMinute()*60, config.getUnknowUserListenIntervalHour()*60*60);
			model.addAttribute("listenMinute", config.getUnknowUserListenTimeMinute());
			if(filter.isIpCanListener(request.getRemoteAddr())){
				user=new User();
				user.setName("游客");
				user.setRole("unknow");
				model.addAttribute("user", user);
				model.addAttribute("sessionId", "no");
			}else{
				return "popupQqRegine";
			}
			
		}
		PublicMessage message=userService.findMessageByKey(PublicMessage.PUBLIC_NEWS);
		if(message!=null){
			model.addAttribute("publicContent", message.getMessageContent());
		}
		Object cuTeacher=request.getServletContext().getAttribute("currentTeacher");
		String teacherName="";
		if(cuTeacher!=null  && cuTeacher instanceof Teacher){
			Teacher teacher=(Teacher)cuTeacher;
			teacherName=teacher.getRealName();
			
		}
		model.addAttribute("currentTeacher", teacherName);
		model.addAttribute("socketIoAddress", socketIoServer.getHostName()+":"+socketIoServer.getPort());
		return "main";
	}
	
}
