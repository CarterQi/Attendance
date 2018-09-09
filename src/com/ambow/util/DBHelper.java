package com.ambow.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Calendar;
import java.util.Properties;

public class DBHelper {

	/*
	 * 1. ���α�����ʱ��һ������ʼ���󲻿ɱ����� ��������ʱ�����ֱ�ӳ�ʼ�������캯�����Գ�ʼ������̬�飬ʵ���� 2.
	 * ���η�����ʱ�򣬷������ɱ���д 3. �������ʱ���಻�ɱ��̳� 4. ���β�����ʱ�򣬲������ɱ�����
	 */
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;

	private static Connection conn;
	private static Statement statement;
	private static PreparedStatement preStatment;
	private static CallableStatement callStatement;
	private static ResultSet set;

	private DBHelper() {

	}

	static {
		try {
			/*
			 * ���Ҫ�õ�ĳһ���ļ���Դ������Ҫ����·��ȥ����
			 */
			InputStream fis = DBHelper.class.getClassLoader()
					.getResourceAsStream("db.properties");

			Properties pro = new Properties();
			pro.load(fis);

			String url = pro.getProperty("URL");
			URL = url;

			String username = pro.getProperty("USERNAME");
			USERNAME = username;

			String password = pro.getProperty("PASSWORD");
			PASSWORD = password;

			fis.close();
			fis = null;
		} catch (Exception e) {

		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ����
	// {call usp_getSalaryByEmpno(?,?)}
	/*
	 * 
	 * CallableStatement callStatement = conn.prepareCall(
	 * "{ call usp_getSalaryByEmpno ( ? ,?) }" ); callStatement.setString(1 ,
	 * "������");
	 * 
	 * callStatement.registerOutParameter(2 , oracle.jdbc.OracleTypes.NUMBER);
	 * callStatement.execute();
	 * 
	 * double sal = callStatement.getDouble(2);
	 */

	public static void execProcedure(String sql, Parameter... params)
			throws SQLException {
		openConn();
		callStatement = conn.prepareCall(sql);

		for (int i = 0; i < params.length; i++) {
			if (params[i].direction == Parameter.IN) {
				if(params[i].paramValue.getClass() == Date.class) {
					callStatement.registerOutParameter(i + 1,
							oracle.jdbc.OracleTypes.DATE);
				}
				callStatement.setObject(i + 1, params[i].paramValue);
			} else if (params[i].direction == Parameter.OUT) {
				if (params[i].paramValue.getClass() == Integer.class
						|| params[i].paramValue.getClass() == Double.class) {
					callStatement.registerOutParameter(i + 1,
							oracle.jdbc.OracleTypes.NUMBER);
				} else if (params[i].paramValue.getClass() == String.class) {
					callStatement.registerOutParameter(i + 1,
							oracle.jdbc.OracleTypes.VARCHAR);
				} else if(params[i].paramValue.getClass() == Date.class) {
					callStatement.registerOutParameter(i + 1,
							oracle.jdbc.OracleTypes.DATE);
				}else{
					callStatement.registerOutParameter(i + 1,
							oracle.jdbc.OracleTypes.OTHER);
				}

				// ��֧������������
			}
		}

		callStatement.execute();

		for (int i = 0; i < params.length; i++) {
			if (params[i].direction == Parameter.OUT) {
				Object obj = callStatement.getObject(i + 1);
				params[i].paramValue = obj;
			}
		}

		closeAll();
	}

	public static ResultSet execQuery(String sql) throws SQLException {
		openConn();
		statement = conn.createStatement();
		set = statement.executeQuery(sql);
		return set;
	}
	
	public static Object[] execScaner(String sql , Object... params)
	{
		return null;
	}

	// select * from emp where empno = ? and ename = ? ----- 4555 ��ѧ��
	public static ResultSet execQuery(String sql, Object... params)
			throws SQLException {
		openConn();
		preStatment = conn.prepareStatement(sql);

		for (int i = 0; i < params.length; i++) {
			// ר��Ϊdate������һ������
			if (params[i].getClass() == java.util.Date.class) {
				// util Dateת���� sql Date
				java.util.Date d = (java.util.Date) params[i];
				java.sql.Date d_end = new java.sql.Date(d.getTime());

				preStatment.setDate(i + 1, d_end);
			} else {
				preStatment.setObject(i + 1, params[i]);
			}
		}

		set = preStatment.executeQuery();

		return set;
	}

	// insert into a values( ? , ? , ? ) , 1 ������ 15
	public static boolean execUpdate(String sql, Object... params)
			throws SQLException {

		openConn();

		preStatment = conn.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			// ר��Ϊdate������һ������
			if (params[i].getClass() == java.util.Date.class) {
				java.util.Date d = (java.util.Date) params[i];
				java.sql.Date d_end = new java.sql.Date(d.getTime());

				preStatment.setDate(i + 1, d_end);
			} else {
				preStatment.setObject(i + 1, params[i]);
			}
		}

		int record = preStatment.executeUpdate();

		closeAll();
		return record > 0;
	}

	// delete from student where id = 1
	public static boolean execUpdate(String sql) throws SQLException {
		openConn();

		Statement statment = conn.createStatement();
		int record = statment.executeUpdate(sql);

		closeAll();
		return record > 0;
	}

	public static void openConn() throws SQLException {
		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

	public static void closeAll() throws SQLException {
		if (set != null) {
			set.close();
			set = null;
		}

		if (callStatement != null) {
			callStatement.close();
			callStatement = null;
		}

		if (preStatment != null) {
			preStatment.close();
			preStatment = null;
		}

		if (statement != null) {
			statement.close();
			statement = null;
		}

		if (conn != null && conn.isClosed() == false) {
			conn.close();
			conn = null;
		}
	}

	public static void display(String... names) {
		for (String string : names) {
			System.out.println(string);
		}
	}

	public static void main(String[] args) {

		try {
			DBHelper.openConn();
			ResultSet set = conn.createStatement().executeQuery(
					"select * from emp");

			/*
			 * 2. DatabaseMetaData ���ݿ�� 1. ResultSetMetaData �����
			 */
			ResultSetMetaData metaData = set.getMetaData();

			if (set.next()) {
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					String name = metaData.getColumnName(i + 1);
					
					Object value = set.getObject(name);
					System.out.print( value==null?"":value.toString() );
				}
				
				System.out.println("");
			}

			set.close();
			DBHelper.closeAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		/*
		 * display(); display("����"); display("����" , "������"); display( new
		 * String[]{"������"} );
		 */

		/*
		 * String sql = "insert into emp(empno , ename) values ( ? , ?  )";
		 * boolean bln = DBHelper.execUpdate(sql, 4455 , "��ѧ��");
		 * System.out.println(bln);
		 */

		/*
		 * ����������͵�����ʲô��������
		 * 
		 * �ܲ��ܼ�⵽ԭʼ��ʵ���ġ�����
		 */

		/*
		 * Object d = new java.util.Date(); if
		 * (d.getClass().getName().equals("java.util.Date")) {
		 * System.out.println("����������"); }
		 * 
		 * if (d.getClass() == java.util.Date.class) {
		 * System.out.println("����������"); }
		 * 
		 * ResultSet set = execQuery("select ename , empno from " +
		 * " emp where job = ? ", "CLERK");
		 * 
		 * System.out.println(set == null);
		 * 
		 * while (set.next()) { String dname = set.getString(1);
		 * System.out.println(dname); }
		 * 
		 * set.close(); set = null;
		 * 
		 * closeAll();
		 */

		/*
		 * ����һ�� Parameter[] params = { new Parameter(7369, Parameter.IN ), new
		 * Parameter(0, Parameter.OUT ) }; String sql =
		 * "{call usp_getSalaryByEmpno(?,?)}";
		 * 
		 * execProcedure(sql, params);
		 * 
		 * Object obj = params[1].paramValue;
		 * System.out.println(obj.toString());
		 */

		/*
		 * String sql = "{call usp_add_dept(?,?,?)}"; Parameter[] params = { new
		 * Parameter(55, Parameter.IN ), new Parameter("С����", Parameter.IN ),
		 * new Parameter("����", Parameter.IN ) };
		 * 
		 * execProcedure(sql, params);
		 */

		/*
		 * String sql = "{call usp_display}"; execProcedure(sql);
		 */

	}

}
