package com.ambow.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Calendar;
import java.util.Properties;

public class DBHelper {

	/*
	 * 1. 修饰变量的时候，一旦被初始化后不可被更改 ，声明的时候可以直接初始化，构造函数可以初始化，静态块，实例块 2.
	 * 修饰方法的时候，方法不可被重写 3. 修饰类的时候，类不可被继承 4. 修饰参数的时候，参数不可被更改
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
			 * 如果要得到某一个文件资源流，不要再用路径去处理。
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

	// 过程
	// {call usp_getSalaryByEmpno(?,?)}
	/*
	 * 
	 * CallableStatement callStatement = conn.prepareCall(
	 * "{ call usp_getSalaryByEmpno ( ? ,?) }" ); callStatement.setString(1 ,
	 * "张三丰");
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

				// 分支处理日期类型
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

	// select * from emp where empno = ? and ename = ? ----- 4555 张学友
	public static ResultSet execQuery(String sql, Object... params)
			throws SQLException {
		openConn();
		preStatment = conn.prepareStatement(sql);

		for (int i = 0; i < params.length; i++) {
			// 专门为date类型做一个处理
			if (params[i].getClass() == java.util.Date.class) {
				// util Date转换成 sql Date
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

	// insert into a values( ? , ? , ? ) , 1 张三丰 15
	public static boolean execUpdate(String sql, Object... params)
			throws SQLException {

		openConn();

		preStatment = conn.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			// 专门为date类型做一个处理
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
			 * 2. DatabaseMetaData 数据库的 1. ResultSetMetaData 结果集
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
		 * display(); display("杨玉环"); display("杨玉环" , "张三丰"); display( new
		 * String[]{"张三丰"} );
		 */

		/*
		 * String sql = "insert into emp(empno , ename) values ( ? , ?  )";
		 * boolean bln = DBHelper.execUpdate(sql, 4455 , "张学友");
		 * System.out.println(bln);
		 */

		/*
		 * 检测数据类型到底是什么数据类型
		 * 
		 * 能不能检测到原始【实例的】类型
		 */

		/*
		 * Object d = new java.util.Date(); if
		 * (d.getClass().getName().equals("java.util.Date")) {
		 * System.out.println("是日期类型"); }
		 * 
		 * if (d.getClass() == java.util.Date.class) {
		 * System.out.println("是日期类型"); }
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
		 * 测试一： Parameter[] params = { new Parameter(7369, Parameter.IN ), new
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
		 * Parameter(55, Parameter.IN ), new Parameter("小卖部", Parameter.IN ),
		 * new Parameter("济南", Parameter.IN ) };
		 * 
		 * execProcedure(sql, params);
		 */

		/*
		 * String sql = "{call usp_display}"; execProcedure(sql);
		 */

	}

}
