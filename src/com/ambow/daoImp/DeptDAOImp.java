package com.ambow.daoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ambow.bean.DeptInfo;
import com.ambow.bean.EmpInfo;
import com.ambow.dao.DeptDAO;
import com.ambow.util.DBHelper;


public class DeptDAOImp implements DeptDAO{
	
	public ArrayList<DeptInfo> query() throws SQLException{
		ArrayList<DeptInfo> list=new ArrayList<DeptInfo>();
	String sql="select departmentid,departmentname,starttimeam,endtimeam,starttimepm,endtimepm,parentid from Att_Department where parentid='0'";
	ResultSet set=DBHelper.execQuery(sql);
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
	DBHelper.closeAll();
	return list;
	}
	public ArrayList<DeptInfo> queryonename(String data) throws SQLException{
		ArrayList<DeptInfo> list=new ArrayList<>();
		String sql="select departmentname from Att_department join Att_AdminPopedom on Att_AdminPopedom.departmentid=Att_department.departmentid "
				+ "join Att_user on Att_user.adminid=Att_adminpopedom.adminid where parentid='0'and adminname=?";
		ResultSet set=DBHelper.execQuery(sql,data);
		while(set.next()) {
			String deptname=set.getString("departmentname");
			DeptInfo info=new DeptInfo(deptname);
			list.add(info);
		}
		if (set != null) {
			set.close();
		}
		DBHelper.closeAll();
		return list;
	}
	public ArrayList<DeptInfo> querytwoname(String name) throws SQLException{
		ArrayList<DeptInfo> list=new ArrayList<>();
		String sql="select departmentname from Att_department where parentid=(select departmentid from Att_department where departmentname=? and parentid='0')";
		ResultSet set=DBHelper.execQuery(sql,name);
		while(set.next()) {
			String deptname=set.getString("departmentname");
			DeptInfo info=new DeptInfo(deptname);
			list.add(info);
		}
		if (set != null) {
			set.close();
		}
		DBHelper.closeAll();
		return list;
	}
	public ArrayList<DeptInfo> querymg(String data) throws SQLException{
		ArrayList<DeptInfo> list=new ArrayList<DeptInfo>();
		String sql="select departmentid,departmentname,starttimeam,endtimeam,starttimepm,endtimepm,parentid from Att_Department where parentid=?";
		ResultSet set=DBHelper.execQuery(sql,data);
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
		DBHelper.closeAll();
		return list;
		
	}
	public void add(DeptInfo dept) throws SQLException{		
		String sql="insert into Att_Department(departmentname,starttimeam,endtimeam,starttimepm,endtimepm,parentid) values(?,?,?,?,?,?)";
		DBHelper.execUpdate(sql,dept.getDepartmentname(),dept.getStarttimeam(),dept.getEndtimeam(),
				dept.getStarttimepm(),dept.getEndtimepm(),dept.getParentid());
	
	}
	public void delete(String data) throws SQLException {
		System.out.println(data);
		String sql="delete from Att_Department where DepartmentID=?";
		DBHelper.execUpdate(sql,data);
	}
	public void update(DeptInfo dept) throws SQLException{
		String sql="update Att_Department set  departmentname=?,starttimeam=?, endtimeam=?,starttimepm=?,endtimepm=? where departmentID=?";
		DBHelper.execUpdate(sql, dept.getDepartmentname(),  dept.getStarttimeam(),dept.getEndtimeam(),
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
