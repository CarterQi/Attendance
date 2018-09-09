package com.ambow.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static final String driverName = "oracle.jdbc.driver.OracleDriver";
	private static final String url= "jdbc:oracle:thin:@localhost:1521:ORCL";
	
	public  static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url,"scott","www663065");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public  static void closeConnection(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

