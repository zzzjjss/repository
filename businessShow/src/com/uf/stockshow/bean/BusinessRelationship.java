package com.uf.stockshow.bean;

import org.neo4j.graphdb.RelationshipType;

public enum BusinessRelationship implements RelationshipType{
	COMPETITIVE,DEPEND,INCLUDE

}
