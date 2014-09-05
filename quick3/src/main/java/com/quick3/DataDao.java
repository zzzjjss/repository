package com.quick3;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataDao {
	String dbName="quick3";
	public void insertOpenResult(OpenResult openResult){
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		try {
			Connection conn = DriverManager.getConnection(connectionURL);
			PreparedStatement preStatement=conn.prepareStatement("insert into openResult(opendate,result,dateIndex) values(?,?,?)");
			preStatement.setDate(1, new Date(openResult.getOpendate().getTime()));
			preStatement.setInt(2, openResult.getResult());
			preStatement.setInt(3, openResult.getDateIndex());
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
			statement.execute("create table openResult (id INTEGER not NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) ,opendate DATE not NULL,dateIndex INTEGER NOT NULL,result INTEGER NOT NULL, PRIMARY KEY (id) )");
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
				int  dateIndex=set.getInt("dateIndex");
				openResult.setOpendate(date);
				openResult.setResult(result);
				openResult.setId(id);
				openResult.setDateIndex(dateIndex);
				all.add(openResult);
			}
			conn.close();
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
		return all;
	}
	
	public int  deleteDataByDate(java.util.Date date){
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		int res=0;
		try {
			Connection conn = DriverManager.getConnection(connectionURL);
			SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd");
			String day=format.format(date);
			PreparedStatement preStatement=conn.prepareStatement("delete  from openResult where opendate = DATE('"+day+"')");
			 res=preStatement.executeUpdate();
			conn.close();
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
		return res;
	}
	public  Map<Integer,OpenResult> findOneDayData(java.util.Date date){
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		Map<Integer,OpenResult> all=new HashMap<Integer, OpenResult>();
		try {
			Connection conn = DriverManager.getConnection(connectionURL);
			SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd");
			String day=format.format(date);
			PreparedStatement preStatement=conn.prepareStatement("select  * from openResult where opendate =  DATE('"+day+"')");
			ResultSet set=preStatement.executeQuery();
			
			while(set.next()){
				OpenResult  openResult=new OpenResult();
				Date  opendate=set.getDate("opendate");
				int result=set.getInt("result");
				int  id=set.getInt("id");
				int  dateIndex=set.getInt("dateIndex");
				openResult.setOpendate(opendate);
				openResult.setResult(result);
				openResult.setId(id);
				openResult.setDateIndex(dateIndex);
				all.put(openResult.getDateIndex(), openResult);
			}
			conn.close();
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
		return all;
	}
	public List<OpenResult> findLastDayData(){
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		List<OpenResult> all=new ArrayList<OpenResult>();
		try {
			Connection conn = DriverManager.getConnection(connectionURL);
			PreparedStatement preStatement=conn.prepareStatement("select  * from openResult where opendate = (select MAX(opendate) from openResult )");
			ResultSet set=preStatement.executeQuery();
			
			while(set.next()){
				OpenResult  openResult=new OpenResult();
				Date  date=set.getDate("opendate");
				int result=set.getInt("result");
				int  id=set.getInt("id");
				int  dateIndex=set.getInt("dateIndex");
				openResult.setOpendate(date);
				openResult.setResult(result);
				openResult.setId(id);
				openResult.setDateIndex(dateIndex);
				all.add(openResult);
			}
			conn.close();
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
		return all;
	}
	public List<OpenResult> findDataBeforeToday(int days){
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		List<OpenResult> all=new ArrayList<OpenResult>();
		try {
			Connection conn = DriverManager.getConnection(connectionURL);
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(new Date(System.currentTimeMillis()));
			calendar.add(Calendar.DAY_OF_YEAR, 0-days);
			SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd");
			String day=format.format(calendar.getTime());
			
			PreparedStatement preStatement=conn.prepareStatement("select  * from openResult where opendate >= DATE('"+day+"')");
			ResultSet set=preStatement.executeQuery();
			
			while(set.next()){
				OpenResult  openResult=new OpenResult();
				Date  date=set.getDate("opendate");
				int result=set.getInt("result");
				int  id=set.getInt("id");
				int  dateIndex=set.getInt("dateIndex");
				openResult.setOpendate(date);
				openResult.setResult(result);
				openResult.setId(id);
				openResult.setDateIndex(dateIndex);
				all.add(openResult);
			}
			conn.close();
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
		return all;
	}
	public Map<Integer,Integer> groupDataBeforeToday(int days){
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		Map<Integer,Integer> result=new HashMap<Integer, Integer>();
		try {
			Connection conn = DriverManager.getConnection(connectionURL);
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(new Date(System.currentTimeMillis()));
			calendar.add(Calendar.DAY_OF_YEAR, 0-days);
			SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd");
			String day=format.format(calendar.getTime());
			PreparedStatement preStatement=conn.prepareStatement("select  count(*) as allcount,result from openResult  where opendate>= DATE('"+day+"') group by result");
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
		dao.dropTable();
		dao.createTable();
		
//		OpenResult  result=new OpenResult();
//		SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			result.setOpendate(format.parse("2014-09-03"));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		result.setResult(13);
//		result.setDateIndex(1);
//		dao.insertOpenResult(result);
		
		
//		Map<Integer,Integer> map=dao.groupLastDaysData(2);
//		System.out.println(map.toString());
		
//	List<OpenResult> results=dao.findLastDayData();	
//	SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd");
//	for(OpenResult r:results){
//		String date=format.format(r.getOpendate());
//		System.out.println(date+":"+r.getResult());
//	}
		
//		int all=dao.findAll().size();
//		Map<Integer,Integer> sum=dao.groupAll();
//		for(Integer r:sum.keySet()){
//			System.out.println(r+":"+(Float.valueOf(sum.get(r))/Float.valueOf(all))*100);
//		}
		
		
		
	}
}
