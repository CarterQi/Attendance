package com.ambow.daoImp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ambow.bean.JobInfo;
import com.ambow.dao.JobDAO;
import com.ambow.util.DBHelper;

public class JobDAOImp implements JobDAO{
	public ArrayList<JobInfo> query() throws SQLException {
		ArrayList<JobInfo> list = new ArrayList<JobInfo>();
	String sql="select positionid,positionname from Att_Position";
	ResultSet set=DBHelper.execQuery(sql);
	while(set.next()) {
		String positionid = set.getString("positionid");
		String positionname = set.getString("positionname");

		JobInfo info=new JobInfo(positionid, positionname);
		list.add(info);
	}
	if (set != null) {
		set.close();
	}
	DBHelper.closeAll();
	return list;
	}
	public void add(JobInfo job) throws SQLException {
		String sql = "INSERT INTO Att_Position (positionname) VALUES(?)";
		
		DBHelper.execUpdate(sql, job.getPositionname());
	}
	
	public void update(JobInfo job) throws SQLException{
		String sql="update Att_Position set  positionname=? where positionid=?";
		DBHelper.execUpdate(sql, job.getPositionname(),job.getPositionid());
	}
	public void delete(String data) throws SQLException {
		String sql="delete from Att_Position where positionid=?";
		DBHelper.execUpdate(sql,data);
	}

}
