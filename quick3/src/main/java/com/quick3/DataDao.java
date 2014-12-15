package com.quick3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataDao {
	String dbName="quick3";
	private Connection conn=null;
	
	protected void finalize() throws Throwable { 
		if(conn!=null){
			conn.close();
		}
	}
	private Connection getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				MysqlDataSource ds = new MysqlDataSource();
				ds.setUrl("jdbc:mysql://localhost:3306/quick3");
				ds.setUser("root");
				ds.setPassword("root");
				conn=ds.getConnection();
//				String connectionURL = "jdbc:derby:" + dbName + ";create=true";
//				Connection conn = DriverManager.getConnection(connectionURL);
			}
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public int getPreMaxTotalIndex(Date date){
		try {
			Connection conn =getConnection();
			SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd");
			String day=format.format(date);
			PreparedStatement preStatement=conn.prepareStatement("select max(total_index) from openResult where opendate < DATE('"+day+"')");
			 ResultSet result=preStatement.executeQuery();
			 if(result.next()){
				return  result.getInt(1);
			 }
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
		return 0;
	}
	public Map<Integer,Integer>  stepStatistic(int openResult){
		Map<Integer,Integer> result=new TreeMap<Integer, Integer>();
		try {
			Connection conn =getConnection();
			PreparedStatement preStatement=conn.prepareStatement("select  * from openresult  o where o.RESULT=? order by o.OPENDATE asc , o.DATEINDEX asc");
			preStatement.setInt(1, openResult);
			ResultSet set=preStatement.executeQuery();
			int preTotalIndex=0;
			while(set.next()){
				int totalIndex=set.getInt("total_index");
				int step=totalIndex-preTotalIndex;
				
				preTotalIndex=totalIndex;
				if(result.get(step)==null){
					result.put(step, 1);
				}else{
					Integer num=result.get(step);
					result.put(step, (num.intValue())+1);
				}
			}
			conn.close();
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
		return result;
	}
	private int countDateDiff(Date pre,Date end){
		long diff=end.getTime()-pre.getTime();
		return (int)Math.abs(diff) / (60 * 60 * 1000 * 24);
	}
	public int findOpenSum(java.util.Date date){
		try {
			Connection conn =getConnection();
			SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd");
			String day=format.format(date);
			PreparedStatement preStatement=conn.prepareStatement("select count(*)  from openResult where opendate = DATE('"+day+"')");
			 ResultSet result=preStatement.executeQuery();
			 if(result.next()){
				return  result.getInt(1);
			 }
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
		return 0;
	}
	public OpenResult findLastOpenResult(int openNumber){
		try {
			Connection conn = getConnection();
			//PreparedStatement preStatement=conn.prepareStatement("select * from openresult o where o.RESULT=?  order by o.OPENDATE desc  FETCH FIRST 1 ROWS ONLY");
			PreparedStatement preStatement=conn.prepareStatement("select * from openresult o where o.RESULT=?  order by o.total_index desc  limit 1");
			preStatement.setInt(1, openNumber);
			ResultSet set=preStatement.executeQuery();
			
			if(set.next()){
				OpenResult  openResult=new OpenResult();
				Date  date=set.getDate("opendate");
				int result=set.getInt("result");
				int  id=set.getInt("id");
				int  dateIndex=set.getInt("dateIndex");
				int totalIndex=set.getInt("total_index");
				openResult.setOpendate(date);
				openResult.setResult(result);
				openResult.setId(id);
				openResult.setDateIndex(dateIndex);
				openResult.setTotalIndex(totalIndex);
				conn.close();
				return openResult;
			}
			
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
		return null;
	}
	
	public int findMaxTotalIndex(){
		try {
			Connection conn =getConnection();
			PreparedStatement preStatement=conn.prepareStatement("select max(total_index)  from openResult ");
			 ResultSet result=preStatement.executeQuery();
			 if(result.next()){
				return  result.getInt(1);
			 }
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
		return 0;
	}
	public void insertOpenResult(OpenResult openResult){
		try {
			Connection conn =getConnection();
			PreparedStatement preStatement=conn.prepareStatement("insert into openResult(opendate,result,dateIndex,total_index) values(?,?,?,?)");
			preStatement.setDate(1, new java.sql.Date(openResult.getOpendate().getTime()));
			preStatement.setInt(2, openResult.getResult());
			preStatement.setInt(3, openResult.getDateIndex());
			preStatement.setInt(4, openResult.getTotalIndex());
			preStatement.execute();
			conn.close();
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
	}
	
	public void createTableIfNotExist(){
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		try {
			Connection conn = DriverManager.getConnection(connectionURL);
			PreparedStatement preStatement=conn.prepareStatement("select  * from sys.SYSTABLES ");
			ResultSet set=preStatement.executeQuery();
			while(set.next()){
				String tablesName=set.getString("TABLENAME");
				if(tablesName.equalsIgnoreCase("openResult")){
					return;
				}
			}
			preStatement.close();
			Statement statement=conn.createStatement();
			statement.execute("create table openResult (id INTEGER not NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) ,opendate DATE not NULL,dateIndex INTEGER NOT NULL,result INTEGER NOT NULL,total_index INTEGER NOT NULL,PRIMARY KEY (id) )");
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
		List<OpenResult> all=new ArrayList<OpenResult>();
		try {
			Connection conn = getConnection();
			PreparedStatement preStatement=conn.prepareStatement("select  * from openResult");
			ResultSet set=preStatement.executeQuery();
			
			while(set.next()){
				OpenResult  openResult=new OpenResult();
				Date  date=set.getDate("opendate");
				int result=set.getInt("result");
				int  id=set.getInt("id");
				int  dateIndex=set.getInt("dateIndex");
				int  totalIndex=set.getInt("total_index");
				openResult.setOpendate(date);
				openResult.setResult(result);
				openResult.setId(id);
				openResult.setTotalIndex(totalIndex);
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
		int res=0;
		try {
			Connection conn = getConnection();
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
	public int countOneDayData(Date date){
		try {
			Connection conn = getConnection();
			SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd");
			String day=format.format(date);
			PreparedStatement preStatement=conn.prepareStatement("select  count(*) from openResult where opendate =  DATE('"+day+"')");
			ResultSet set=preStatement.executeQuery();
			
			while(set.next()){
				return set.getInt(1);
			}
			conn.close();
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
		return 0;
	}
	
	public  Map<Integer,OpenResult> findOneDayData(java.util.Date date){
		Map<Integer,OpenResult> all=new HashMap<Integer, OpenResult>();
		try {
			Connection conn = getConnection();
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
				int  totalIndex=set.getInt("total_index");
				openResult.setOpendate(opendate);
				openResult.setResult(result);
				openResult.setId(id);
				openResult.setDateIndex(dateIndex);
				openResult.setTotalIndex(totalIndex);
				all.put(openResult.getDateIndex(), openResult);
			}
			conn.close();
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
		return all;
	}
	public List<OpenResult> findLastDayData(){
		List<OpenResult> all=new ArrayList<OpenResult>();
		try {
			Connection conn =getConnection();
			PreparedStatement preStatement=conn.prepareStatement("select  * from openResult where opendate = (select MAX(opendate) from openResult )");
			ResultSet set=preStatement.executeQuery();
			
			while(set.next()){
				OpenResult  openResult=new OpenResult();
				Date  date=set.getDate("opendate");
				int result=set.getInt("result");
				int  id=set.getInt("id");
				int  dateIndex=set.getInt("dateIndex");
				int  totalIndex=set.getInt("total_index");
				openResult.setOpendate(date);
				openResult.setResult(result);
				openResult.setId(id);
				openResult.setDateIndex(dateIndex);
				openResult.setTotalIndex(totalIndex);
				all.add(openResult);
			}
			conn.close();
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
		return all;
	}
	public List<OpenResult> findDataBeforeToday(int days){
		List<OpenResult> all=new ArrayList<OpenResult>();
		try {
			Connection conn = getConnection();
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
				int  totalIndex=set.getInt("total_index");
				openResult.setOpendate(date);
				openResult.setResult(result);
				openResult.setId(id);
				openResult.setDateIndex(dateIndex);
				openResult.setTotalIndex(totalIndex);
				all.add(openResult);
			}
			conn.close();
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
		return all;
	}
	public Map<Integer,Integer> groupDataBeforeToday(int days){
		Map<Integer,Integer> result=new HashMap<Integer, Integer>();
		try {
			Connection conn = getConnection();
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
		Map<Integer,Integer> result=new HashMap<Integer, Integer>();
		try {
			Connection conn =getConnection();
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
	public int findLatestOpenResultToNextStep(int openNumber){
		OpenResult preOpen=findLastOpenResult(openNumber);
		int maxTotalIndex=findMaxTotalIndex();
		return maxTotalIndex-preOpen.getTotalIndex();
	}
	public static void main(String[] args) {
		DataDao dao=new DataDao();
//		dao.getConnection();
		Map<Integer,Map<Integer,Integer>> allNumberStepStatistic=new TreeMap<Integer, Map<Integer,Integer>>();
		long begin=System.currentTimeMillis();
		for(int i=3;i<=18;i++){
			Map<Integer,Integer> statistic=dao.stepStatistic(i);
			allNumberStepStatistic.put(i, statistic);
			System.out.println((System.currentTimeMillis()-begin)/1000);
		}
//		Map<Integer,Integer> statis=dao.stepStatistic(3);
//		int sum=0;
//		for(Integer key:statis.keySet()){
//			int value=statis.get(key);
//			sum=sum+value;
//		}
//		double sumRation=0.0d;
//		for(Integer key:statis.keySet()){
//			int value=statis.get(key);
//			double ration=((double)value/(double)sum)*100;
//			sumRation=sumRation+ration;
//			System.out.println("key:"+key+"--->"+value+"--->"+ration);
//			System.out.println(sumRation);
//		}
//		for(int i=3;i<=18;i++){
//			System.out.println(i+"--->"+dao.findLatestOpenResultToNextStep(i));
//		}
		
		//dao.createTableIfNotExist();
		//dao.dropTable();
		//dao.createTable();
		
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
