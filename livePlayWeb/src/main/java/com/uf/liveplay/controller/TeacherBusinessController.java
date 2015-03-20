package com.uf.liveplay.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uf.liveplay.entity.Teacher;
import com.uf.liveplay.service.UserService;
import com.uf.liveplay.socketio.SocketIoServer;

@Controller
public class TeacherBusinessController {
	@Autowired
	private UserService userService;
	@Autowired
	private SocketIoServer socketIoServer;
	
	@RequestMapping("/teacher/login")
	@ResponseBody
	public String login(@RequestParam Map<String,String> allRequestParams,HttpServletRequest request){
		String userName=allRequestParams.get("userName");
		String password=allRequestParams.get("password");
		try{
			if(userService.teacherLogin(userName,password)){
				Teacher teacher=userService.findTeacherByName(userName);
				request.getSession().setAttribute("teacher", teacher);
				request.getServletContext().setAttribute("currentTeacher", teacher);
				socketIoServer.switchCurrentTeacher(teacher.getRealName());
				return "ok";
			}else{
				return "用户名或密码错误！";
			}
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	
	@RequestMapping("/teacher/control/saveTeacherInfo")
	@ResponseBody
	public String saveTeacherInfo(@RequestParam Map<String,String> allRequestParams,HttpServletRequest request){
		String userId=allRequestParams.get("userId");
		String oldPwd=allRequestParams.get("oldPassword");
		String newPwd=allRequestParams.get("newPassword");
		String realName=allRequestParams.get("realName");
		
		try {
			Teacher user=userService.findTeacherById(Integer.parseInt(userId));
			if(user==null){
				return "用户不存在！";
			}else if(!user.getPassword().equals(oldPwd)){
				return "旧密码错误！";
			}else{
				user.setPassword(newPwd);
				user.setRealName(realName);
				userService.saveTeacherInfo(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "ok";
	}
	
	@RequestMapping("/teacher/control/teacherMain")
	public String teacherMain(ModelMap model,HttpServletRequest request){
		Object obj=request.getSession().getAttribute("teacher");
		String context=request.getServletContext().getContextPath();
		model.addAttribute("context",context);
		Teacher teacher=null;
		if(obj instanceof Teacher){
			teacher=(Teacher)obj;
			model.addAttribute("teacher",teacher);
			model.addAttribute("sessionId", request.getSession().getId());
		}
		model.addAttribute("socketIoAddress", socketIoServer.getHostName()+":"+socketIoServer.getPort());
		return "teacherMain";
	}
	@RequestMapping("/teacher/control/logout")
	@ResponseBody
	public String logout(ModelMap model,HttpServletRequest request){
		request.getSession().invalidate();
		return "ok";
	}
 
}
