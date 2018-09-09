package com.ambow.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ambow.bean.DeptInfo;
import com.ambow.bean.UserInfo;

public interface PowerDAO {
	public ArrayList<DeptInfo> query() throws SQLException;
	public ArrayList<UserInfo> queryname() throws SQLException;
	public ArrayList<DeptInfo> queryBypk(String data) throws SQLException;
	public void add(String adminname,String deptid) throws SQLException;
}
