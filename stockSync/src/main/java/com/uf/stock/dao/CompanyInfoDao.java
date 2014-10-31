package com.uf.stock.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.uf.stock.bean.CompanyInfo;

public class CompanyInfoDao {
	public void addCompanyInfo(CompanyInfo  companyInfo){
		Connection con=null;
		PreparedStatement preState=null;
		try {
			con=DBConnectionUtil.getDBConnection();
			preState=con.prepareStatement("insert into company_info(stock_code) values(?) ");
			preState.setString(1, companyInfo.getStockCode());
			preState.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
