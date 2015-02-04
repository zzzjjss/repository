package com.uf.live.util;

import java.util.HashMap;
import java.util.Map;

public class QueryStringParser {
	public Map<String, String> parseQuery(String queryString){
		Map<String ,String> result=new HashMap<String, String>();
		if(queryString!=null){
			if(queryString.startsWith("?")){
				String temp=queryString.replaceFirst("[?]", "");
				String attrs[]=temp.split("&");
				if(attrs!=null&attrs.length>0){
					for(String attr:attrs){
						String param[]=attr.split("=");
						if(param!=null&&param.length>1){
							result.put(param[0], param[1]);
						}
					}
				}
			}
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		QueryStringParser parser=new QueryStringParser();
		System.out.println(parser.parseQuery("?name=jason&password=zhang"));
	}
}
