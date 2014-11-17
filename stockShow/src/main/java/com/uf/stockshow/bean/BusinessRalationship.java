package com.uf.stockshow.bean;

import org.neo4j.graphdb.RelationshipType;

public enum BusinessRalationship implements RelationshipType{
	COMPETITIVE,DEPEND,INCLUDE

}
