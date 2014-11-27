package com.uf.stockshow.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uf.stockshow.bean.Business;
import com.uf.stockshow.bean.BusinessRelationship;
import com.uf.stockshow.bean.RelationShip;
import com.uf.stockshow.dao.Neo4jDao;

@Controller
public class BusinessController {
	private Neo4jDao dao=new Neo4jDao();
	
	@RequestMapping("/contentMain")
	public String contentMain(){
		return "contentMain";
	}
	@RequestMapping("/relationshipMain")
	public String relationshipMain(ModelMap model){
		List<Business> allBusiness=dao.findAllNodes();
		List<String> relations=new ArrayList<String>();
		relations.add(BusinessRelationship.COMPETITIVE.getName());
		relations.add(BusinessRelationship.DEPEND.getName());
		relations.add(BusinessRelationship.INCLUDE.getName());
		model.addAttribute("nodes", allBusiness);
		model.addAttribute("relations", relations);
		return "relationshipMain";
	}
	@RequestMapping("/relationshipShow")
	public String relationshipShow(){
		return "relationshipShow";
	}
	
	@RequestMapping("/addRelation")
	@ResponseBody
	public  String addRelation(@RequestParam Map<String,String> allRequestParams){
		String busFrom=allRequestParams.get("busFrom");
		String busTo=allRequestParams.get("busTo");
		String relation=allRequestParams.get("relationship");
		
		
		List<String> busTos=Arrays.asList(busTo.split(","));
		Business  fromBus=new Business();
		fromBus.setId(Long.parseLong(busFrom));
		List<Business> allToBuss=new ArrayList<Business>();
		for(String to:busTos){
			Business  toBus=new Business();
			toBus.setId(Long.parseLong(to));
			allToBuss.add(toBus);
		}
		BusinessRelationship rela=getBusinessRelationshipByName(relation);
		dao.addNodeRelationship(fromBus, allToBuss,rela);
		return "ok";
	}
	private BusinessRelationship getBusinessRelationshipByName(String name){
		if(BusinessRelationship.COMPETITIVE.getName().equalsIgnoreCase(name))
			return BusinessRelationship.COMPETITIVE;
		if(BusinessRelationship.DEPEND.getName().equalsIgnoreCase(name))
			return BusinessRelationship.DEPEND;
		if(BusinessRelationship.INCLUDE.getName().equalsIgnoreCase(name))
			return BusinessRelationship.INCLUDE;
		return null;
	}
	
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
	
	@RequestMapping("/findAllRelationship")
	@ResponseBody
	public String findAllRelationship(){
		List<RelationShip> allRela=dao.findAllRelationships();
		return toRelationshipJson(allRela);
	}
	private String toRelationshipJson(List<RelationShip> allRelation){
		JSONArray  array=new JSONArray();
		for(RelationShip rela:allRelation){
			JSONObject  obj=new JSONObject();
			obj.put("from", rela.getFrom());
			obj.put("to", rela.getTo());
			obj.put("label", rela.getName());
			array.add(obj);
		}
		return array.toString();
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
