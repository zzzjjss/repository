package com.uf.stock.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CompanyInfoDao {
	public void addStockCode(String  stockCode){
		Connection con=null;
		PreparedStatement preState=null;
		try {
			con=DBConnectionUtil.getDBConnection();
			preState=con.prepareStatement("insert into company_info(stock_code) values(?) ");
			preState.setString(1, stockCode);
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
	public void addCompanyBusinessContet(String  businessContent){
		Connection con=null;
		PreparedStatement preState=null;
		try {
			con=DBConnectionUtil.getDBConnection();
			preState=con.prepareStatement(" update company_info set stock_code=? ");
			preState.setString(1, businessContent);
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
}
