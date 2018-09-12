package com.ambow.daoImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ambow.bean.CheckInfo;
import com.ambow.dao.CheckDAO;
import com.ambow.util.DBUtil;

public class CheckDAOImp implements CheckDAO{

	public ArrayList<CheckInfo> query(String data) throws SQLException{//Ô±¹¤¿¼ÇÚ
		ArrayList<CheckInfo> list=new ArrayList<CheckInfo>();
		Connection conn=DBUtil.getConnection();
		String sql="select employeename,cardnumber,departmentname,attendancememo,typename,noteid from Att_Employees"
				+ " left join Att_department on Att_employees.departmentid=Att_department.departmentid left join"
				+ " Att_attendancerecord on Att_employees.employeeid=Att_attendancerecord.employeeid left join Att_attendancetype on typeid=attendancetypeid where departmentname=?";
		conn.prepareStatement(sql);
		ResultSet set = DBUtil.execQuery(sql,data);
		while(set.next()) {
			String employeename=set.getString("employeename");
			String cardnumber=set.getString("cardnumber");
			String typename=set.getString("typename");
			String noteid=set.getString("noteid");
			String departmentname=set.getString("departmentname");
			String attendancememo=set.getString("attendancememo");
			CheckInfo info=new CheckInfo(employeename,cardnumber,typename,noteid,departmentname,attendancememo);
			list.add(info);
		}
		if (set != null) {
			set.close();
		}
		DBUtil.closeConnection(conn, set);
		return list;
	}
	public void update(CheckInfo info,String name) throws SQLException {
		//String sql1="delete Att_attendancerecord where noteid=";
		String flag="";
		Connection conn=DBUtil.getConnection();
		String sql="insert into Att_attendanceRecord(employeeid,attendancememo,attendancedate,attendanceflag,attendancetypeid,adminid,noteid)"
				+ "values((select employeeid from Att_employees where employeename=?),?,?,?,(select typeid from Att_attendancetype where typename=?),"
				+ "(select adminid from Att_user where adminname=?),?)";
		conn.prepareStatement(sql);
		if(info.getFlag().equals("ÉÏÎç")) {
			flag="1";
		}else {
			flag="2";
		}
		DBUtil.execUpdate(sql, info.getEmployeename(),info.getAttendancememo(),info.getDate(),flag,info.getTypename(),name,info.getNoteid());
	}
	
}
