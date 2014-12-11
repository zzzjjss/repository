package com.quick3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class DataDao {
	String dbName="quick3";
	public Map<Integer,Integer>  stepStatistic(int openResult){
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		Map<Integer,Integer> result=new TreeMap<Integer, Integer>();
		try {
			Connection conn = DriverManager.getConnection(connectionURL);
			PreparedStatement preStatement=conn.prepareStatement("select  * from openresult  o where o.RESULT=? order by o.OPENDATE asc , o.DATEINDEX asc");
			preStatement.setInt(1, openResult);
			ResultSet set=preStatement.executeQuery();
			Date  preDate=null;
			int preIndex=0;
			SimpleDateFormat  format=new SimpleDateFormat("YYYY-MM-dd");
			while(set.next()){
				Date  date=set.getDate("opendate");
				int index=set.getInt("dateindex");
				int step=0;
				if(preDate==null)
					preDate=date;
				int dateDif=countDateDiff(preDate,date);
				if(dateDif>1){
					int dayOpen=findOpenSum(preDate);
					step=(dayOpen-preIndex)+index;
					for(int i=1;i<dateDif;i++){
						GregorianCalendar cal=new GregorianCalendar();
						cal.setTime(preDate);
						cal.add(Calendar.DAY_OF_MONTH, i);
						int sum=findOpenSum(cal.getTime());
						step=step+sum;
					}
				}else if(dateDif==1){
					int dayOpen=findOpenSum(preDate);
					step=(dayOpen-preIndex)+index;
				}else{
					step=index-preIndex-1;
				}
				
				if(result.get(step)==null){
					result.put(step, 1);
				}else{
					Integer num=result.get(step);
					result.put(step, (num.intValue())+1);
				}
				
				preDate=date;
				preIndex=index;
				
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
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		try {
			Connection conn = DriverManager.getConnection(connectionURL);
			SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd");
			String day=format.format(date);
			PreparedStatement preStatement=conn.prepareStatement("select count(*)  from openResult where opendate = DATE('"+day+"')");
			 ResultSet result=preStatement.executeQuery();
			 if(result.next()){
				return  result.getInt(1);
			 }
			 
			conn.close();
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
		return 0;
	}
	public OpenResult findLastOpenResult(int openNumber){
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		try {
			Connection conn = DriverManager.getConnection(connectionURL);
			PreparedStatement preStatement=conn.prepareStatement("select * from openresult o where o.RESULT=?  order by o.OPENDATE desc  FETCH FIRST 1 ROWS ONLY");
			preStatement.setInt(1, openNumber);
			ResultSet set=preStatement.executeQuery();
			
			if(set.next()){
				OpenResult  openResult=new OpenResult();
				Date  date=set.getDate("opendate");
				int result=set.getInt("result");
				int  id=set.getInt("id");
				int  dateIndex=set.getInt("dateIndex");
				openResult.setOpendate(date);
				openResult.setResult(result);
				openResult.setId(id);
				openResult.setDateIndex(dateIndex);
				conn.close();
				return openResult;
			}
			
		}  catch (Throwable e)  {   
		   e.printStackTrace();
		}
		return null;
	}
	public void insertOpenResult(OpenResult openResult){
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		try {
			Connection conn = DriverManager.getConnection(connectionURL);
			PreparedStatement preStatement=conn.prepareStatement("insert into openResult(opendate,result,dateIndex) values(?,?,?)");
			preStatement.setDate(1, new java.sql.Date(openResult.getOpendate().getTime()));
			preStatement.setInt(2, openResult.getResult());
			preStatement.setInt(3, openResult.getDateIndex());
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
	public int findLatestOpenResultToNextStep(int openNumber){
		OpenResult preOpen=findLastOpenResult(openNumber);
		Date preDate=preOpen.getOpendate();
		int preIndex=preOpen.getDateIndex();
		GregorianCalendar todayCal=new GregorianCalendar();
		todayCal.setTime(new Date());
		todayCal.set(Calendar.HOUR_OF_DAY, 0);
		todayCal.set(Calendar.MINUTE, 0);
		todayCal.set(Calendar.SECOND, 0);
		int step=0;
		int dateDif=countDateDiff(preDate,todayCal.getTime());
		if(dateDif>1){
			int dayOpen=findOpenSum(preDate);
			step=(dayOpen-preIndex)+findOpenSum(todayCal.getTime());
			for(int i=1;i<dateDif;i++){
				GregorianCalendar cal=new GregorianCalendar();
				cal.setTime(preDate);
				cal.add(Calendar.DAY_OF_MONTH, i);
				int sum=findOpenSum(cal.getTime());
				step=step+sum;
			}
		}else if(dateDif==1){
			int dayOpen=findOpenSum(preDate);
			step=(dayOpen-preIndex)+findOpenSum(todayCal.getTime());
		}else{
			step=79-preIndex-1;
		}
		return step;
	}
	public static void main(String[] args) {
		DataDao dao=new DataDao();
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
		for(int i=3;i<=18;i++){
			System.out.println(i+"--->"+dao.findLatestOpenResultToNextStep(i));
		}
		
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
