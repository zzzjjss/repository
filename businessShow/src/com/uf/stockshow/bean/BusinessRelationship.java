package com.uf.stockshow.bean;

import org.neo4j.graphdb.RelationshipType;

public enum BusinessRelationship implements RelationshipType{
	COMPETITIVE("Competitive"),DEPEND("Depent"),INCLUDE("Include");
	String name;
	 BusinessRelationship(String name){
		this.name=name;
	}
	 public String getName(){
		 return name;
	 }
}
