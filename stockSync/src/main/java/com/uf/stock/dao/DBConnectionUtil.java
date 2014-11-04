package com.uf.stock.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionUtil {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/stock?useUnicode=true&characterEncoding=utf8";
	static final String USER = "root";
	static final String PASS = "root";

	public static Connection getDBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
