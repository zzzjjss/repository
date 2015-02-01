package com.uf.rest.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Session implements Serializable{
	private Map<String, Object> attr=new HashMap<String, Object>();
	public void addAttribute(String key ,Object value){
			attr.put(key, value);
	}
	public Object getAttribute(String key){
		return attr.get(key);
	}
}
