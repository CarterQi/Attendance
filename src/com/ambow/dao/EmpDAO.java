package com.ambow.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ambow.bean.CheckInfo;
import com.ambow.bean.EmpInfo;

public interface EmpDAO {
	public ArrayList<EmpInfo> query() throws SQLException;
	public void add(EmpInfo emp) throws SQLException;
	public void delete(int data) throws SQLException;
	public void update(EmpInfo emp) throws SQLException;
	public ArrayList<CheckInfo> checkemp() throws SQLException;
	public ArrayList<EmpInfo> queryname() throws SQLException;
}
