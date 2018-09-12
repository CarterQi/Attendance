package com.ambow.daoImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.ambow.bean.UserInfo;
import com.ambow.dao.UserDAO;
import com.ambow.util.DBUtil;

public class UserDAOImp implements UserDAO{
	
	public ArrayList<UserInfo> query() throws SQLException{
		ArrayList<UserInfo> list=new ArrayList<UserInfo>();
		Connection conn=DBUtil.getConnection();
	String sql="select adminid,adminname from Att_User";
	conn.prepareStatement(sql);
	ResultSet set=DBUtil.execQuery(sql);
	while(set.next()) {
		String adminid = set.getString("adminid");
		String adminname = set.getString("adminname");
		UserInfo info=new UserInfo(adminid, adminname);
		list.add(info);
		
	}
	if (set != null) {
		set.close();
	}
	DBUtil.closeConnection(conn, set);
	return list;
	}
   public void add(UserInfo user) throws SQLException{		
		String sql="insert into Att_User(adminname,password) values(?,?)";
		DBUtil.execUpdate(sql,user.getAdminName(),user.getPassword());
	
	}
	public void delete(String data) throws SQLException {
		String sql="delete from Att_User where adminid=?";
		DBUtil.execUpdate(sql,data);
	}
	public void update(UserInfo user) throws SQLException{
		String sql="update Att_User set adminname=? where adminID=?";
		DBUtil.execUpdate(sql, user.getAdminName(),user.getAdminID());
	}
	
	
}
