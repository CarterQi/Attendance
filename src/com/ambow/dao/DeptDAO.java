package com.ambow.dao;


import java.sql.SQLException;
import java.util.ArrayList;


import com.ambow.bean.DeptInfo;


public interface DeptDAO {
	public ArrayList<DeptInfo> query() throws SQLException;
	public void delete(String data) throws SQLException;
	public void update(DeptInfo dept) throws SQLException;
	public void add(DeptInfo dept) throws SQLException;
	public ArrayList<DeptInfo> queryonename() throws SQLException;
	public ArrayList<DeptInfo> querytwoname() throws SQLException;
}
