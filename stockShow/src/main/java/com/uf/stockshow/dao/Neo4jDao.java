package com.uf.stockshow.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.traversal.Traverser;

import com.uf.stockshow.bean.Business;
import com.uf.stockshow.bean.BusinessRalationship;
import com.uf.stockshow.bean.NetGraph;

public class Neo4jDao {
	private  GraphDatabaseService db =DbFactory.getDb();
	public void addBusinessNodes(List<Business> businesses){
		
	}
	
	public List<Business> findAllNodes(){
		return null;
	}
	
	public void addNodeRelationship(Business from ,List<Business> to,BusinessRalationship raltionship){
		
	}
	
	public NetGraph getNodeNetGraph(long nodeId){
		NetGraph graph=new NetGraph();
		Node node=db.getNodeById(nodeId);
		Traverser traverser=db.traversalDescription().relationships(BusinessRalationship.DEPEND).traverse(node);
		
		for(Node n:traverser.nodes()){
			JSONObject  nodeObj=new JSONObject();
			nodeObj.put("id", n.getId());
			nodeObj.put("label", n.getProperty("name"));
			graph.getNodes().add(nodeObj);
		}
		
		for(Relationship relation:traverser.relationships()){
			JSONObject  edgeObj=new JSONObject();
			edgeObj.put("from", relation.getStartNode().getId());
			edgeObj.put("to", relation.getEndNode().getId());
			edgeObj.put("label", relation.getType().name());
			graph.getEdges().add(edgeObj);
		}
		return graph;
	}
}
