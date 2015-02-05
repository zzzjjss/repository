package com.uf.liveplay.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uf.liveplay.entity.User;
import com.uf.liveplay.service.UserService;

@Controller
public class UserBusinessController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/registNewUser")
	@ResponseBody
	public String registNewUser(@RequestParam Map<String,String> allRequestParams,HttpServletRequest request){
		User user=new User();
		user.setName(allRequestParams.get("userName"));
		user.setPassword(allRequestParams.get("password"));
		user.setPhone(allRequestParams.get("phone"));
		user.setRole("commonUser");
		try {
			if(userService.findUserByName(user.getName())!=null){
				return "用户已存在，请使用别的用户名！";
			}else{
				userService.registNewUser(user);
				request.getSession().setAttribute("user", user);
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
		System.out.println(userName+password);
		try{
			if(userService.login(userName,password)){
				User user=userService.findUserByName(userName);
				request.getSession().setAttribute("user", user);
				return "ok";
			}else{
				return "fail";
			}
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
	}
	@RequestMapping("/controller/logout")
	@ResponseBody
	public String logout(ModelMap model,HttpServletRequest request){
		Object obj=request.getSession().getAttribute("user");
		if(obj!=null){
			request.getSession().removeAttribute("user");
		}
		return "ok";
	}
	@RequestMapping("/views/main")
	public String mainPage(ModelMap model,HttpServletRequest request){
		Object obj=request.getSession().getAttribute("user");
		User user=null;
		if(obj instanceof User){
			user=(User)obj;
			model.addAttribute("user",user);
			model.addAttribute("sessionId", request.getSession().getId());
		}else{
			model.addAttribute("error", "user not login!");
			return "error";
		}
		
		return "main";
	}
	
}
