package com.ambow.daoImp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ambow.bean.JobInfo;
import com.ambow.dao.JobDAO;
import com.ambow.util.DBUtil;

public class JobDAOImp implements JobDAO{
	public ArrayList<JobInfo> query() throws SQLException {
		ArrayList<JobInfo> list = new ArrayList<JobInfo>();
		Connection conn=DBUtil.getConnection();
	String sql="select positionid,positionname from Att_Position";
	conn.prepareStatement(sql);
	ResultSet set=DBUtil.execQuery(sql);
	while(set.next()) {
		String positionid = set.getString("positionid");
		String positionname = set.getString("positionname");

		JobInfo info=new JobInfo(positionid, positionname);
		list.add(info);
	}
	if (set != null) {
		set.close();
	}
	DBUtil.closeConnection(conn, set);
	return list;
	}
	public void add(JobInfo job) throws SQLException {
		String sql = "INSERT INTO Att_Position (positionname) VALUES(?)";
		
		DBUtil.execUpdate(sql, job.getPositionname());
	}
	
	public void update(JobInfo job) throws SQLException{
		String sql="update Att_Position set  positionname=? where positionid=?";
		DBUtil.execUpdate(sql, job.getPositionname(),job.getPositionid());
	}
	public void delete(String data) throws SQLException {
		String sql="delete from Att_Position where positionid=?";
		DBUtil.execUpdate(sql,data);
	}

}
