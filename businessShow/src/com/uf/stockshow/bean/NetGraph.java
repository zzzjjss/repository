package com.uf.stockshow.bean;

import net.sf.json.JSONArray;

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
	
}
