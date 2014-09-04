package com.quick3;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataDao {
	String dbName="quick3";
	public void insertOpenResult(OpenResult openResult){
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		try {
			Connection conn = DriverManager.getConnection(connectionURL);
			PreparedStatement preStatement=conn.prepareStatement("insert into openResult(opendate,result) values(?,?)");
			preStatement.setDate(1, new Date(openResult.getOpendate().getTime()));
			preStatement.setInt(2, openResult.getResult());
			preStatement.execute();
			conn.close();
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
	}
	
	
	public void createTable(){
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		try {
			Connection conn = DriverManager.getConnection(connectionURL);
			Statement statement=conn.createStatement();
			statement.execute("create table openResult (id INTEGER not NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) ,opendate DATE not NULL,result INTEGER NOT NULL, PRIMARY KEY (id) )");
			statement.close();
			conn.close();
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
	}
	public void dropTable(){
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		try {
			Connection conn = DriverManager.getConnection(connectionURL);
			Statement statement=conn.createStatement();
			statement.execute("drop table openResult ");
			statement.close();
			conn.close();
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
	}
	public List<OpenResult> findAll(){
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		List<OpenResult> all=new ArrayList<OpenResult>();
		try {
			Connection conn = DriverManager.getConnection(connectionURL);
			PreparedStatement preStatement=conn.prepareStatement("select  * from openResult");
			ResultSet set=preStatement.executeQuery();
			
			while(set.next()){
				OpenResult  openResult=new OpenResult();
				Date  date=set.getDate("opendate");
				int result=set.getInt("result");
				int  id=set.getInt("id");
				openResult.setOpendate(date);
				openResult.setResult(result);
				openResult.setId(id);
				all.add(openResult);
			}
			conn.close();
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
		return all;
	}
	public Map<Integer,Integer> groupAll(){
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		Map<Integer,Integer> result=new HashMap<Integer, Integer>();
		try {
			Connection conn = DriverManager.getConnection(connectionURL);
			PreparedStatement preStatement=conn.prepareStatement("select  count(*) as allcount,result from openResult group by result");
			ResultSet set=preStatement.executeQuery();
			
			while(set.next()){
				int  count=set.getInt("allcount");
				int openresult=set.getInt("result");
				result.put(openresult, count);
			}
			conn.close();
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args) {
		DataDao dao=new DataDao();
//		dao.dropTable();
//		dao.createTable();
		int all=dao.findAll().size();
		Map<Integer,Integer> sum=dao.groupAll();
		for(Integer r:sum.keySet()){
			System.out.println(r+":"+(Float.valueOf(sum.get(r))/Float.valueOf(all))*100);
		}
		
		
		
	}
}
