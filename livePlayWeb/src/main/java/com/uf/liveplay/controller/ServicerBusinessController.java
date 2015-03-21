package com.uf.liveplay.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.uf.liveplay.entity.Servicer;
import com.uf.liveplay.entity.User;
import com.uf.liveplay.service.UserService;
import com.uf.liveplay.socketio.SocketIoServer;
import com.uf.liveplay.unit.ConfigVariable;
import com.uf.liveplay.unit.ServletWebsocketBridge;

@Controller
public class ServicerBusinessController {
	@Autowired
	private UserService userService;
	@Autowired
	private ConfigVariable config;
	@Autowired
	private SocketIoServer socketIoServer;
	@RequestMapping("/servicer/control/registNewCommonUser")
	@ResponseBody
	public String registNewUser(@RequestParam Map<String,String> allRequestParams,HttpServletRequest request){
		User user=new User();
		user.setName(allRequestParams.get("userName"));
		user.setPassword("123456");
		user.setPhone(allRequestParams.get("phone"));
		user.setRole("commonUser");
		user.setCreateTime(new Date());
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
			if(userService.servicerLogin(userName,password)){
				Servicer servicer=userService.findServicerByName(userName);
				request.getSession().setAttribute("servicer", servicer);
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
			Servicer user=userService.findServicerById(Integer.parseInt(userId));
			if(user==null){
				return "用户不存在！";
			}else if(!user.getPassword().equals(oldPwd)){
				return "旧密码错误！";
			}else{
				user.setPassword(newPwd);
				userService.saveServicerInfo(user);
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
		Servicer user=null;
		if(obj instanceof Servicer){
			user=(Servicer)obj;
			model.addAttribute("servicer",user);
		}
		model.addAttribute("socketIoAddress", socketIoServer.getHostName()+":"+socketIoServer.getPort());
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
		List<User> allUser=userService.findAllCommonUser();
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
				SimpleDateFormat formate=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
				obj.put("createTime", user.getCreateTime()==null?"":formate.format(user.getCreateTime()));
				jsonArray.add(obj);
			}
		}
		return jsonArray.toString();
	}
	
	@RequestMapping("/servicer/control/shutupUserMouth")
	@ResponseBody
	public String shutupUserMouth(@RequestParam Map<String,String> allRequestParams, HttpServletRequest request) {
		String userId=allRequestParams.get("userId");
		if(userId!=null&&!userId.trim().equals("")){
			socketIoServer.shutupUserMouth(Integer.parseInt(userId.trim()));
		}
		return "ok";
	}
}
