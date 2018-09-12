package com.ambow.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
public class DBUtil {
	private static  String DRIVERNAME;
	private static  String URL;
	private static  String USERNAME;
	private static  String USERPWD;//以上为从db.propertie取得的数据库配置
	static {
		try { 
			InputStream fis = DBUtil.class.getClassLoader()
					.getResourceAsStream("db.properties");
			Properties pro = new Properties();
			pro.load(fis);
			URL = pro.getProperty("URL");
			USERNAME = pro.getProperty("USERNAME");
			USERPWD = pro.getProperty("USERPWD");
			DRIVERNAME=pro.getProperty("DRIVERNAME");
			fis.close();
			fis = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Class.forName(DRIVERNAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @author TianZW
	 * @throws SQLException 
	 * */
	public static ResultSet execQuery(String sql, Object... params)
			throws SQLException {
		Connection conn=DBUtil.getConnection();
		PreparedStatement state = conn.prepareStatement(sql);

		for (int i = 0; i < params.length; i++) {
			// 专门为date类型做一个处理
			if (params[i].getClass() == java.util.Date.class) {
				// util Date转换成 sql Date
				java.util.Date d = (java.util.Date) params[i];
				java.sql.Date d_end = new java.sql.Date(d.getTime());

				state.setDate(i + 1, d_end);
			} else {
				state.setObject(i + 1, params[i]);
			}
		}

		ResultSet set = state.executeQuery();
		return set;
	}

	// insert into a values( ? , ? , ? ) , 1 张三丰 15
	public static boolean execUpdate(String sql, Object... params)
			throws SQLException {

		Connection conn=DBUtil.getConnection();
		PreparedStatement state = conn.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			// 专门为date类型做一个处理
			if (params[i].getClass() == java.util.Date.class) {
				java.util.Date d = (java.util.Date) params[i];
				java.sql.Date d_end = new java.sql.Date(d.getTime());

				state.setDate(i + 1, d_end);
			} else {
				state.setObject(i + 1, params[i]);
			}
		}

		int record = state.executeUpdate();


		return record > 0;
	}
	public static Connection getConnection() throws SQLException  {
		Connection conn = DriverManager.getConnection(URL, USERNAME, USERPWD);
		return conn;
	}
	/**
	 * @author TianZW
	 * 关闭链接
	 * */
	public static void closeConnection(Connection conn,ResultSet set){
		if(set != null)
			try {
				set.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String [] args) throws SQLException{//测试数据库是否链接成功
		
		Connection conn=DBUtil.getConnection();
		PreparedStatement state = 
				conn.prepareStatement("select * from att_User");
		ResultSet rs = state.executeQuery();
		if( rs.next() )
		{
			System.out.println("success!");
		}
		DBUtil.closeConnection(conn,rs);
	}
}