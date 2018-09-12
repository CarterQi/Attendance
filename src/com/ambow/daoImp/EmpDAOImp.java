package com.ambow.daoImp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ambow.bean.CheckInfo;
import com.ambow.bean.EmpInfo;
import com.ambow.bean.PositionInfo;
import com.ambow.dao.EmpDAO;
import com.ambow.util.DBUtil;
import com.ambow.util.PageUtil;


public class EmpDAOImp implements EmpDAO{
	
	public ArrayList<EmpInfo> query(PageUtil pageUtil) throws SQLException {
		
		ArrayList<EmpInfo> list = new ArrayList<EmpInfo>();
		Connection conn=DBUtil.getConnection();
		String sql="select EmployeeID,EmployeeName,EmployeeGender,PositionName,DepartmentName,CardNumber,EmployeeState,EmployeeMemo from Att_Employees"
				+ " left join Att_department on Att_department.departmentid=Att_employees.departmentid left join Att_position on Att_position.positionid=Att_employees.positionid";
		conn.prepareStatement(sql);
		ResultSet set = DBUtil.execQuery(sql);
	while(set.next()) {
		
		String EmployeeID = set.getString("EmployeeID");
		String EmployeeName = set.getString("EmployeeName");
		String EmployeeGender = set.getString("EmployeeGender");
		String PositionName = set.getString("PositionName");
		String DepartmentName = set.getString("DepartmentName");
		String CardNumber = set.getString("CardNumber");
		String EmployeeState = set.getString("EmployeeState");
		String EmployeeMemo = set.getString("EmployeeMemo");
		EmpInfo info=new EmpInfo(EmployeeID, EmployeeName, EmployeeGender, PositionName, DepartmentName, CardNumber,EmployeeState, EmployeeMemo);
		list.add(info);
		}
	
	if (set != null) {
		set.close();
	}
	DBUtil.closeConnection(conn, set);
	return list;
	}

	public ArrayList<EmpInfo> queryname() throws SQLException{
		ArrayList<EmpInfo> list =new ArrayList<EmpInfo>();
		Connection conn=DBUtil.getConnection();
		String sql="select employeename from Att_employees";
		conn.prepareStatement(sql);
		ResultSet set = DBUtil.execQuery(sql);
		while(set.next()) {
			String employeename=set.getString("employeename");
			EmpInfo info=new EmpInfo(employeename);
			list.add(info);
		}
		if (set != null) {
			set.close();
		}
		DBUtil.closeConnection(conn, set);
		return list;
	}
	public ArrayList<PositionInfo> querypositionname() throws SQLException{
		ArrayList<PositionInfo> list =new ArrayList<PositionInfo>();
		Connection conn=DBUtil.getConnection();
		String sql="select positionname from Att_position";
		conn.prepareStatement(sql);
		ResultSet set = DBUtil.execQuery(sql);
		while(set.next()) {
			String positionname=set.getString("positionname");
			PositionInfo info=new PositionInfo(positionname);
			list.add(info);
		}
		if (set != null) {
			set.close();
		}
		DBUtil.closeConnection(conn, set);
		return list;
		
	}
	public void add(EmpInfo emp) throws SQLException {
		String sql = "INSERT INTO Att_Employees (EmployeeName,EmployeeGender,PositionID,DepartmentID,CardNumber,EmployeeState,EmployeeMemo) VALUES(?,?,(select positionid from Att_position where positionname=?),(select departmentid from Att_department where departmentname=?),?,?,?)";
		
		DBUtil.execUpdate(sql, emp.getEmployeeName(),  emp.getEmployeeGender(),emp.getPositionName(),
				emp.getDepartmentName(), emp.getCardNumber(), emp.getEmployeeState(),emp.getEmployeeMemo());
	}
	
	public void update(EmpInfo emp) throws SQLException{
		String sql="update Att_Employees set  EmployeeName=?,EmployeeGender=?, PositionID=(select positionid from Att_position where positionname=?),DepartmentID=(select departmentid from Att_department where departmentname=?),CardNumber=?,EmployeeState=?,EmployeeMemo=? where EmployeeID=?";
		DBUtil.execUpdate(sql, emp.getEmployeeName(),  emp.getEmployeeGender(),emp.getPositionName(),
				emp.getDepartmentName(), emp.getCardNumber(), emp.getEmployeeState(),emp.getEmployeeMemo(),emp.getEmployeeID());
	}
	public void delete(int data) throws SQLException {
		String sql="delete from Att_Employees where EmployeeID=?";
		DBUtil.execUpdate(sql,data);
	}

	@Override
	public ArrayList<EmpInfo> query() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CheckInfo> checkemp() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}



}
