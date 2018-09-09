package com.ambow.daoImp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ambow.bean.CheckInfo;
import com.ambow.bean.EmpInfo;
import com.ambow.dao.EmpDAO;
import com.ambow.util.DBHelper;
import com.ambow.util.PageUtil;


public class EmpDAOImp implements EmpDAO{
	
	public ArrayList<EmpInfo> query(PageUtil pageUtil) throws SQLException {
		
		ArrayList<EmpInfo> list = new ArrayList<EmpInfo>();
		ResultSet set = DBHelper
				.execQuery("select EmployeeID,EmployeeName,EmployeeGender,PositionID,DepartmentID,CardNumber,EmployeeState,EmployeeMemo from Att_Employees");

		
	while(set.next()) {
		
		int EmployeeID = set.getInt("EmployeeID");
		String EmployeeName = set.getString("EmployeeName");
		String EmployeeGender = set.getString("EmployeeGender");
		int PositionID = set.getInt("PositionID");
		int DepartmentID = set.getInt("DepartmentID");
		String CardNumber = set.getString("CardNumber");
		String EmployeeState = set.getString("EmployeeState");
		String EmployeeMemo = set.getString("EmployeeMemo");
		EmpInfo info=new EmpInfo(EmployeeID, EmployeeName, EmployeeGender, PositionID, DepartmentID, CardNumber,EmployeeState, EmployeeMemo);
		list.add(info);
		}
	
	if (set != null) {
		set.close();
	}
	DBHelper.closeAll();
	return list;
	}

	public ArrayList<EmpInfo> queryname() throws SQLException{
		ArrayList<EmpInfo> list =new ArrayList<EmpInfo>();
		String sql="select employeename from Att_employees";
		ResultSet set = DBHelper.execQuery(sql);
		while(set.next()) {
			String employeename=set.getString("employeename");
			EmpInfo info=new EmpInfo(employeename);
			list.add(info);
		}
		if (set != null) {
			set.close();
		}
		DBHelper.closeAll();
		return list;
	}
	public ArrayList<CheckInfo> checkemp(String data) throws SQLException{//Ô±¹¤¿¼ÇÚ
		ArrayList<CheckInfo> list=new ArrayList<CheckInfo>();
		String sql="select employeename,cardnumber,departmentname,attendancememo from Att_Employees"
				+ " left join Att_department on Att_employees.departmentid=Att_department.departmentid left join"
				+ " Att_attendancerecord on Att_employees.employeeid=Att_attendancerecord.employeeid where departmentname=?";
		ResultSet set = DBHelper.execQuery(sql,data);
		while(set.next()) {
			String employeename=set.getString("employeename");
			String cardnumber=set.getString("cardnumber");
			String departmentname=set.getString("departmentname");
			String attendancememo=set.getString("attendancememo");
			CheckInfo info=new CheckInfo(employeename,cardnumber,departmentname,attendancememo);
			list.add(info);
		}
		if (set != null) {
			set.close();
		}
		DBHelper.closeAll();
		return list;
	}
	
	public void add(EmpInfo emp) throws SQLException {
		String sql = "INSERT INTO Att_Employees (EmployeeName,EmployeeGender,PositionID,DepartmentID,CardNumber,EmployeeState,EmployeeMemo) VALUES(?,?,?,?,?,?,?)";
		
		DBHelper.execUpdate(sql, emp.getEmployeeName(),  emp.getEmployeeGender(),emp.getPositionID(),
				emp.getDepartmentID(), emp.getCardNumber(), emp.getEmployeeState(),emp.getEmployeeMemo());
	}
	
	public void update(EmpInfo emp) throws SQLException{
		String sql="update Att_Employees set  EmployeeName=?,EmployeeGender=?, PositionID=?,DepartmentID=?,CardNumber=?,EmployeeState=?,EmployeeMemo=? where EmployeeID=?";
		DBHelper.execUpdate(sql, emp.getEmployeeName(),  emp.getEmployeeGender(),emp.getPositionID(),
				emp.getDepartmentID(), emp.getCardNumber(), emp.getEmployeeState(),emp.getEmployeeMemo(),emp.getEmployeeID());
	}
	public void delete(int data) throws SQLException {
		String sql="delete from Att_Employees where EmployeeID=?";
		DBHelper.execUpdate(sql,data);
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
