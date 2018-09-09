package com.ambow.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ambow.bean.LeaveInfo;
import com.ambow.bean.NotesInfo;

public interface LeaveDAO {
	public ArrayList<LeaveInfo> queryname() throws SQLException;
	public ArrayList<NotesInfo> query(String name) throws SQLException;
}
