package com.uf.stockshow.bean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class NetGraph {
	private JSONArray nodes;
	private JSONArray edges;
	public JSONArray getNodes() {
		return nodes;
	}
	public void setNodes(JSONArray nodes) {
		this.nodes = nodes;
	}
	public JSONArray getEdges() {
		return edges;
	}
	public void setEdges(JSONArray edges) {
		this.edges = edges;
	}
	public String toString(){
		JSONObject  obj=new JSONObject();
		obj.put("nodes", nodes);
		obj.put("edges", edges);
		return obj.toString();
	}
}
