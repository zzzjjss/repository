package com.uf.stockshow.dao;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.traversal.Traverser;
import org.neo4j.tooling.GlobalGraphOperations;

import com.uf.stockshow.bean.Business;
import com.uf.stockshow.bean.BusinessRelationship;
import com.uf.stockshow.bean.NetGraph;
import com.uf.stockshow.bean.RelationShip;

public class Neo4jDao {
	public static void main(String[] args) {
		Neo4jDao dao=new Neo4jDao();
		dao.findAllNodes();
	}
	private  GraphDatabaseService db =DbFactory.getDb();
	public void addBusinessNodes(List<Business> businesses){
		Transaction tras=db.beginTx();
		for(Business bus:businesses){
			Node node=db.createNode();
			node.setProperty("name", bus.getName());
			tras.success();
		}
		
		tras.close();
	}
	
	public List<RelationShip> findAllRelationships(){
		List<RelationShip> result=new ArrayList<RelationShip>();
		Transaction tras=db.beginTx();
		GlobalGraphOperations global=GlobalGraphOperations.at(db);
		for(Relationship relation:global.getAllRelationships()){
			RelationShip  re=new RelationShip();
			re.setFrom(relation.getStartNode().getId());
			re.setTo(relation.getEndNode().getId());
			re.setName(relation.getType().name());
			result.add(re);
		}
		tras.close();
		return result;
	}
	public List<Business> findAllNodes(){
		List<Business> result=new ArrayList<Business>();
		Transaction tras=db.beginTx();
		GlobalGraphOperations global=GlobalGraphOperations.at(db);
		for(Node node:global.getAllNodes()){
			Business business=new Business();
			business.setId(node.getId());
			business.setName((String)node.getProperty("name"));
			result.add(business);
			
		}
		tras.close();
		return result;
	}
	
	public void addNodeRelationship(Business from ,List<Business> to,BusinessRelationship raltionship){
		Transaction tras=db.beginTx();
		Node node = db.getNodeById(from.getId());
		for(Business bus:to){
			Node toNode=db.getNodeById(bus.getId());
			node.createRelationshipTo(toNode, raltionship);
			tras.success();
		}
		tras.close();
	}
	
	public NetGraph getNodeNetGraph(long nodeId){
		Transaction tras=db.beginTx();
		NetGraph graph=new NetGraph();
		Node node=db.getNodeById(nodeId);
		Traverser traverser=db.traversalDescription().relationships(BusinessRelationship.DEPEND).traverse(node);
		
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
		tras.close();
		return graph;
	}
}
