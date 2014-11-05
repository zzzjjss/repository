package com.uf.stock.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uf.stock.bean.CompanyInfo;

public class CompanyInfoDao {
	public void addStockCodeAndName(String  stockCode,String name){
		Connection con=null;
		PreparedStatement preState=null;
		try {
			con=DBConnectionUtil.getDBConnection();
			preState=con.prepareStatement("insert into company_info(stock_code,company_name) values(?,?) ");
			preState.setString(1, stockCode);
			preState.setString(2, name);
			preState.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
				try {
					if(preState!=null)
						preState.close();
					if(con!=null)
						con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	public void addCompanyBusinessContet(String stockCode,String  businessContent){
		Connection con=null;
		PreparedStatement preState=null;
		try {
			con=DBConnectionUtil.getDBConnection();
			preState=con.prepareStatement(" update company_info set business_content=? where stock_code=?");
			preState.setString(1, businessContent);
			preState.setString(2, stockCode);
			preState.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
				try {
					if(preState!=null)
						preState.close();
					if(con!=null)
						con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	public List<CompanyInfo> findAll(){
		Connection con=null;
		PreparedStatement preState=null;
		List<CompanyInfo>  infos=new ArrayList<CompanyInfo>();
		try {
			con=DBConnectionUtil.getDBConnection();
			preState=con.prepareStatement(" select * from company_info ");
			ResultSet result=preState.executeQuery();
			
			while(result.next()){
				String stockCode=result.getString("stock_code");
				String name=result.getString("company_name");
				String content=result.getString("business_content");
				if(content!=null){
					CompanyInfo info=new CompanyInfo();
					info.setStockCode(stockCode);
					info.setCompanyName(name);
					info.setBusinessContent(content);
					infos.add(info);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
				try {
					if(preState!=null)
						preState.close();
					if(con!=null)
						con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return infos;
	}
}
