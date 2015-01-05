package com.uf.rest.bean;

import java.util.HashMap;
import java.util.Map;

public class Session {
	private Map<String, Object> attr=new HashMap<String, Object>();
	public void addAttribute(String key ,Object value){
			attr.put(key, value);
	}
	public Object getAttribute(String key){
		return attr.get(key);
	}
}
