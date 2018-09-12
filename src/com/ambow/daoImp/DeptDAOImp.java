package com.ambow.daoImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ambow.bean.DeptInfo;

import com.ambow.dao.DeptDAO;
import com.ambow.util.DBUtil;


public class DeptDAOImp implements DeptDAO{
	
	public ArrayList<DeptInfo> query() throws SQLException{
		ArrayList<DeptInfo> list=new ArrayList<DeptInfo>();
		Connection conn=DBUtil.getConnection();
	String sql="select departmentid,departmentname,starttimeam,endtimeam,starttimepm,endtimepm,parentid from Att_Department where parentid='0'";
	conn.prepareStatement(sql);
	ResultSet set=DBUtil.execQuery(sql);
	while(set.next()) {
		String departmentid = set.getString("departmentid");
		String departmentname = set.getString("departmentname");
		String starttimeam = set.getString("starttimeam");
		String endtimeam = set.getString("endtimeam");
		String starttimepm = set.getString("starttimepm");
		String endtimepm = set.getString("endtimepm");
		String parentid = set.getString("parentid");
		DeptInfo info=new DeptInfo(departmentid, departmentname, starttimeam, endtimeam,starttimepm,endtimepm,parentid);
		list.add(info);
		
	}
	if (set != null) {
		set.close();
	}
	DBUtil.closeConnection(conn, set);
	return list;
	}
	public ArrayList<DeptInfo> queryonename(String data) throws SQLException{
		ArrayList<DeptInfo> list=new ArrayList<>();
		Connection conn=DBUtil.getConnection();
		String sql="select departmentname from Att_department join Att_AdminPopedom on Att_AdminPopedom.departmentid=Att_department.departmentid "
				+ "join Att_user on Att_user.adminid=Att_adminpopedom.adminid where parentid='0'and adminname=?";
		conn.prepareStatement(sql);
		ResultSet set=DBUtil.execQuery(sql,data);
		while(set.next()) {
			String deptname=set.getString("departmentname");
			DeptInfo info=new DeptInfo(deptname);
			list.add(info);
		}
		if (set != null) {
			set.close();
		}
		DBUtil.closeConnection(conn, set);
		return list;
	}
	public ArrayList<DeptInfo> querytwoname(String name) throws SQLException{
		ArrayList<DeptInfo> list=new ArrayList<>();
		Connection conn=DBUtil.getConnection();
		String sql="select departmentname from Att_department where parentid=(select departmentid from Att_department where departmentname=? and parentid='0')";
		conn.prepareStatement(sql);
		ResultSet set=DBUtil.execQuery(sql,name);
		while(set.next()) {
			String deptname=set.getString("departmentname");
			DeptInfo info=new DeptInfo(deptname);
			list.add(info);
		}
		if (set != null) {
			set.close();
		}
		DBUtil.closeConnection(conn, set);
		return list;
	}
	public ArrayList<DeptInfo> queryname() throws SQLException{
		ArrayList<DeptInfo> list=new ArrayList<>();
		Connection conn=DBUtil.getConnection();
		String sql="select departmentname from Att_department where parentid<>'0'";
		conn.prepareStatement(sql);
		ResultSet set=DBUtil.execQuery(sql);
		while(set.next()) {
			String deptname=set.getString("departmentname");
			DeptInfo info=new DeptInfo(deptname);
			list.add(info);
		}
		if (set != null) {
			set.close();
		}
		DBUtil.closeConnection(conn, set);
		return list;
	}
	public ArrayList<DeptInfo> querymg(String data) throws SQLException{
		ArrayList<DeptInfo> list=new ArrayList<DeptInfo>();
		Connection conn=DBUtil.getConnection();
		String sql="select departmentid,departmentname,starttimeam,endtimeam,starttimepm,endtimepm,parentid from Att_Department where parentid=?";
		conn.prepareStatement(sql);
		ResultSet set=DBUtil.execQuery(sql,data);
		while(set.next()) {
			String departmentid = set.getString("departmentid");
			String departmentname = set.getString("departmentname");
			String starttimeam = set.getString("starttimeam");
			String endtimeam = set.getString("endtimeam");
			String starttimepm = set.getString("starttimepm");
			String endtimepm = set.getString("endtimepm");
			String parentid = set.getString("parentid");
			DeptInfo info=new DeptInfo(departmentid, departmentname, starttimeam, endtimeam,starttimepm,endtimepm,parentid);
			list.add(info);
			
		}
		if (set != null) {
			set.close();
		}
		DBUtil.closeConnection(conn, set);
		return list;
		
	}
	public void add(DeptInfo dept) throws SQLException{		
		String sql="insert into Att_Department(departmentname,starttimeam,endtimeam,starttimepm,endtimepm,parentid) values(?,?,?,?,?,?)";
		DBUtil.execUpdate(sql,dept.getDepartmentname(),dept.getStarttimeam(),dept.getEndtimeam(),
				dept.getStarttimepm(),dept.getEndtimepm(),dept.getParentid());
	
	}
	public void delete(String data) throws SQLException {
		System.out.println(data);
		String sql="delete from Att_Department where DepartmentID=?";
		DBUtil.execUpdate(sql,data);
	}
	public void update(DeptInfo dept) throws SQLException{
		String sql="update Att_Department set  departmentname=?,starttimeam=?, endtimeam=?,starttimepm=?,endtimepm=? where departmentID=?";
		DBUtil.execUpdate(sql, dept.getDepartmentname(),  dept.getStarttimeam(),dept.getEndtimeam(),
				dept.getStarttimepm(), dept.getEndtimepm(), dept.getDepartmentid());
	}
	@Override
	public ArrayList<DeptInfo> querytwoname() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<DeptInfo> queryonename() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
