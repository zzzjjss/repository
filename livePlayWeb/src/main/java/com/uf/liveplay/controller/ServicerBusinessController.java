package com.uf.liveplay.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uf.liveplay.entity.User;
import com.uf.liveplay.service.UserService;
import com.uf.liveplay.unit.ConfigVariable;
import com.uf.liveplay.unit.RegistFilter;
import com.uf.liveplay.unit.UnknowUserFilter;

@Controller
public class ServicerBusinessController {
	@Autowired
	private UserService userService;
	@Autowired
	private ConfigVariable config;
	
	@RequestMapping("/servicer/control/registNewCommonUser")
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
			}
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return "ok";
	}
	@RequestMapping("/servicer/login")
	@ResponseBody
	public String login(@RequestParam Map<String,String> allRequestParams,HttpServletRequest request){
		String userName=allRequestParams.get("userName");
		String password=allRequestParams.get("password");
		try{
			if(userService.login(userName,password)){
				User user=userService.findUserByName(userName);
				request.getSession().setAttribute("servicer", user);
				return "ok";
			}else{
				return "用户名或密码错误！";
			}
		}catch(Exception e){
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	
	@RequestMapping("/servicer/control/saveServicerInfo")
	public String saveServicerInfo(@RequestParam Map<String,String> allRequestParams,HttpServletRequest request){
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
	
	@RequestMapping("/servicer/control/servicerMain")
	public String servicerMain(ModelMap model,HttpServletRequest request){
		Object obj=request.getSession().getAttribute("servicer");
		String context=request.getServletContext().getContextPath();
		model.addAttribute("context",context);
		User user=null;
		if(obj instanceof User){
			user=(User)obj;
			model.addAttribute("servicer",user);
		}
		model.addAttribute("wsAddress", config.getWebSocketAddress());
		return "servicerMain";
	}
	@RequestMapping("/servicer/control/logout")
	@ResponseBody
	public String logout(ModelMap model,HttpServletRequest request){
		request.getSession().invalidate();
		return "ok";
	}
	
	@RequestMapping("/servicer/control/getAllUser")
	@ResponseBody
	public String getAllUserAction(ModelMap model, HttpServletRequest request) {
		List<User> allUser=userService.findAllUser();
		JSONObject result = new JSONObject();
		result.put("data", convertToJsonArray(allUser));
		return result.toString();
	}
	private String convertToJsonArray(List<User> users){
		JSONArray  jsonArray=new JSONArray();
		if(users!=null){
			for(User user:users){
				JSONObject obj=new JSONObject();
				obj.put("id", user.getId());
				obj.put("name", user.getName()==null?" ":user.getName());
				obj.put("phone", user.getPhone()==null?" ":user.getPhone());
				jsonArray.add(obj);
			}
		}
		return jsonArray.toString();
	}
}
