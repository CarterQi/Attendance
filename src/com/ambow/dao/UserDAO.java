package com.ambow.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ambow.bean.UserInfo;

public interface UserDAO {
	public ArrayList<UserInfo> query() throws SQLException;
	public void add(UserInfo user) throws SQLException;
	public void delete(String data) throws SQLException;
	public void update(UserInfo user) throws SQLException;
}
