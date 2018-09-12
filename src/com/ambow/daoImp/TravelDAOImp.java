package com.ambow.daoImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ambow.bean.NotesInfo;
import com.ambow.dao.TravelDAO;
import com.ambow.util.DBUtil;

public class TravelDAOImp implements TravelDAO{
	public ArrayList<NotesInfo> query(String name) throws SQLException{
	ArrayList<NotesInfo> list= new ArrayList<NotesInfo>();
	Connection conn=DBUtil.getConnection();
	String sql="select noteid,fillintime,cause,adminid,startdate,E.employeename employeename,typename,starttime,endtime,N.employeename operatorname from Att_notes left join Att_employees E "
			+ "on E.employeeid=Att_notes.employeeid left join Att_employees N on N.employeeid=Att_notes.operatorid left join Att_attendancetype on notetypeid=typeid where adminid=(select adminid from Att_User where adminname=?) and typecategory='0'";
	conn.prepareStatement(sql);
	ResultSet set=DBUtil.execQuery(sql,name);
	while(set.next()) {
		String noteid=set.getString("noteid");
		String fillintime=set.getString("fillintime");
		String cause=set.getString("cause");
		String adminid=set.getString("adminid");
		String startdate=set.getString("startdate");
		String employeename=set.getString("employeename");
		String typename=set.getString("typename");
		String starttime=set.getString("starttime");
		String endtime=set.getString("endtime");
		String operatorname=set.getString("operatorname");
		NotesInfo info=new NotesInfo(noteid,fillintime,cause,adminid,employeename,operatorname,typename,startdate,starttime,endtime);
		list.add(info);
	}
	if(set!=null) {
		set.close();
	}
	DBUtil.closeConnection(conn, set);
	return list;
  }
	public void add(NotesInfo note) throws SQLException {

		String sql="insert into Att_notes(employeeid,notetypeid,"
				+ "fillintime,startdate,starttime,endtime,cause,adminid,directorsign,projectname,operatorid)"
				+ "values((select employeeid from Att_employees where employeename=?),"
				+ "'22',?,?,?,?,?,(select adminid from Att_user where adminname=?),?,?,?)";
		DBUtil.execUpdate(sql, note.getEmployeename(),note.getFillintime(),
				note.getStarttime(),note.getStarttime(),note.getEndtime(),note.getCause(),note.getAdminname(),note.getDirectorsign(),
				note.getProjectname(),note.getOperatorid());

}
public void update(NotesInfo note,String noteid) throws SQLException{
		
		String sql="update Att_Notes set fillintime=?,startdate=?,employeeid=(select employeeid from Att_employees where employeename=?),"
				+ "notetypeid=(select typeid from Att_AttendanceType where typename=?),cause=?,adminid=?,starttime=?,"
				+ "endtime=?,operatorid=(select employeeid from Att_employees where employeename=?) where noteid=?";
		
		DBUtil.execUpdate(sql,note.getFillintime(),note.getStartdate(),note.getEmployeename(),note.getTypename(),note.getCause(),note.getAdminid(),note.getStarttime(),note.getEndtime(),note.getOperatorname(),noteid);
	}
public void delete(String data) throws SQLException {
	String sql="delete from Att_notes where noteid=?";
	DBUtil.execUpdate(sql,data);
}
}
