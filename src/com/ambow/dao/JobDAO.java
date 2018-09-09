package com.ambow.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ambow.bean.JobInfo;


public interface JobDAO {
	public ArrayList<JobInfo> query() throws SQLException;
	public void add(JobInfo job) throws SQLException;
	public void update(JobInfo job) throws SQLException;
	public void delete(String data) throws SQLException;
}
