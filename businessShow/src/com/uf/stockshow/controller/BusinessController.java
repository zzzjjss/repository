package com.uf.stockshow.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uf.stockshow.bean.Business;
import com.uf.stockshow.dao.Neo4jDao;

@Controller
public class BusinessController {
	private Neo4jDao dao=new Neo4jDao();
	@RequestMapping("/addBusiness")
	@ResponseBody
	public  String addBusiness(@RequestParam Map<String,String> allRequestParams){
		String business=allRequestParams.get("business");
		if(business==null||business.trim().equals("")){
			return "ok";
		}
		List<String> bus=Arrays.asList(business.split(","));
		List<Business> busss=new ArrayList<Business>();
		for(String b:bus){
			Business bb=new Business();
			bb.setName(b);
			busss.add(bb);
		}
		if(busss.size()>0){
			dao.addBusinessNodes(busss);
		}
		
		return "ok";
	}
	@RequestMapping("/findAllNodes")
	@ResponseBody
	public String findAllNodes(){
		List<Business> allBusiness=dao.findAllNodes();
		return toNodesJson(allBusiness);
	}
	private String toNodesJson(List<Business> allBusiness){
		JSONArray  array=new JSONArray();
		for(Business bus:allBusiness){
			JSONObject  obj=new JSONObject();
			obj.put("id", bus.getId());
			obj.put("label", bus.getName());
			array.add(obj);
		}
		return array.toString();
		
	}
}
