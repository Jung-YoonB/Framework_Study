package com.kh.spring.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "C##JDBC";
	private static final String PASSWORD = "JDBC";
	
	public static Connection getDBConn() throws SQLException {
		Connection conn = null;
		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
				
		return conn;
	}
}
