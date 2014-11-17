package com.uf.stockshow.dao;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class DbFactory {
	private static GraphDatabaseService db;
	{
		GraphDatabaseFactory factory=new GraphDatabaseFactory();
		db=factory.newEmbeddedDatabase("business");
	}
	
	public static GraphDatabaseService getDb(){
		return db;
	}
}
