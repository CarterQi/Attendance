package com.ambow.daoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ambow.bean.LeaveInfo;
import com.ambow.bean.NotesInfo;
import com.ambow.dao.LeaveDAO;
import com.ambow.util.DBHelper;

public class LeaveDAOImp implements LeaveDAO{

	public ArrayList<LeaveInfo> queryname() throws SQLException{
		ArrayList<LeaveInfo> list=new ArrayList<LeaveInfo>();
		String sql="select typeid,typename from Att_Attendancetype";
		ResultSet set=DBHelper.execQuery(sql);
		while(set.next()) {
			String typeid=set.getString("typeid");
			String typename=set.getString("typename");
			LeaveInfo info=new LeaveInfo(typeid,typename);
			list.add(info);
		}
		if(set!=null) {
			set.close();
		}
		DBHelper.closeAll();
		return list;
	}
	public ArrayList<NotesInfo> query(String name) throws SQLException{
		ArrayList<NotesInfo> list= new ArrayList<NotesInfo>();
		String sql="select noteid,fillintime,cause,adminid,startdate,E.employeename,typename,starttime,endtime,N.employeename from Att_notes left join Att_employees E "
				+ "on E.employeeid=Att_notes.employeeid left join Att_employees N on N.employeeid=Att_notes.operatorid left join Att_attendancetype on notetypeid=typeid where adminid=(select adminid from Att_User where adminname=?)";
		ResultSet set=DBHelper.execQuery(sql,name);
		while(set.next()) {
			String noteid=set.getString("noteid");
			String fillintime=set.getString("fillintime");
			String cause=set.getString("cause");
			String adminid=set.getString("adminid");
			String startdate=set.getString("startdate");
			String employeename=set.getString("E.employeename");
			String typename=set.getString("typename");
			String starttime=set.getString("starttime");
			String endtime=set.getString("endtime");
			String operatorname=set.getString("N.employeename");
			NotesInfo info=new NotesInfo(noteid,fillintime,cause,adminid,employeename,operatorname,typename,startdate,starttime,endtime);
			list.add(info);
		}
		if(set!=null) {
			set.close();
		}
		DBHelper.closeAll();
		return list;
		
	}
	
}
