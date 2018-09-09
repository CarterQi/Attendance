package com.ambow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ambow.util.DBUtil;

public class LoginDao {
	
	
	public boolean query(String username,String password) throws SQLException{
		Connection conn=DBUtil.getConnection();
		PreparedStatement state = conn.prepareStatement("select * from Att_User where AdminName=? and password=?");
		state.setString(1, username);
		state.setString(2, password);
		ResultSet set=state.executeQuery();
		//System.out.println(set.next());
		if(set.next()) {

			set.close();
			state.close();
			conn.close();
			return true;
		}
		set.close();
		state.close();
		conn.close();
		return false;
		
	}

}
