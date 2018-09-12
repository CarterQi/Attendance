package com.ambow.daoImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ambow.bean.DeptInfo;
import com.ambow.bean.UserInfo;
import com.ambow.dao.PowerDAO;
import com.ambow.util.DBUtil;

public class PowerDAOImp implements PowerDAO{
	public ArrayList<DeptInfo> query() throws SQLException{
		ArrayList<DeptInfo> list=new ArrayList<DeptInfo> ();
		Connection conn=DBUtil.getConnection();
		String sql="select parentid,departmentname,departmentid from Att_Department";
		conn.prepareStatement(sql);
		ResultSet set=DBUtil.execQuery(sql);
		while(set.next()) {
			String departmentname = set.getString("departmentname");
			String departmentid = set.getString("departmentid");
			String parentid=set.getString("parentid");
			DeptInfo info=new DeptInfo(departmentname,parentid,departmentid);
			list.add(info);
			
		}
		if (set != null) {
			set.close();
		}
		DBUtil.closeConnection(conn, set);
		return list;
	}
	public ArrayList<UserInfo> queryname() throws SQLException{
		ArrayList<UserInfo> list=new ArrayList<UserInfo>();
		Connection conn=DBUtil.getConnection();
		String sql="select AdminName,AdminID from Att_User where AdminName<>'admin'";
		conn.prepareStatement(sql);
		ResultSet set=DBUtil.execQuery(sql);
		while(set.next()) {
			String adminname=set.getString("AdminName");
			String adminid=set.getString("AdminID");
			UserInfo info=new UserInfo(adminid,adminname);
			list.add(info);
		}
		if (set != null) {
			set.close();
		}
		DBUtil.closeConnection(conn, set);
		return list;
	}
	public ArrayList<DeptInfo> queryBypk(String data) throws SQLException{
		ArrayList<DeptInfo> list=new ArrayList<DeptInfo>();
		Connection conn=DBUtil.getConnection();
		String sql="select parentid,departmentname,Att_Department.departmentid from Att_Department left join Att_AdminPopedom on Att_Department.DepartmentID=Att_AdminPopedom.DepartmentID"
				+ " left join Att_User on Att_User.AdminID=Att_AdminPopedom.AdminID where AdminName=?";
		conn.prepareStatement(sql);
		ResultSet set=DBUtil.execQuery(sql,data);
		while(set.next()) {
			String departmentname = set.getString("departmentname");
			String departmentid = set.getString("departmentid");
			String parentid=set.getString("parentid");
			DeptInfo info=new DeptInfo(departmentname,parentid,departmentid);
			list.add(info);
			
		}
		if (set != null) {
			set.close();
		}
		DBUtil.closeConnection(conn, set);
		return list;
		
	}
	public void add(String adminname,String deptid) throws SQLException {
		String sql1="select adminid from Att_user where adminname=?";
		Connection conn=DBUtil.getConnection();
		conn.prepareStatement(sql1);
		ResultSet set=DBUtil.execQuery(sql1,adminname);
		while(set.next()) {
			String adminid=set.getString("adminid");
			String sql="insert into Att_AdminPopedom(adminid,departmentid) values(?,?)";
			DBUtil.execUpdate(sql,adminid,deptid);
		}
		if (set != null) {
			set.close();
		}

		DBUtil.closeConnection(conn, set);
		
	}
	
	
}
