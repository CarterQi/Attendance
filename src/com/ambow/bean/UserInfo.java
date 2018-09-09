package com.ambow.bean;

public class UserInfo {

	private String AdminID;
	private String AdminName;
	private String password;
	public String getAdminID() {
		return AdminID;
	}
	public void setAdminID(String adminID) {
		AdminID = adminID;
	}
	public String getAdminName() {
		return AdminName;
	}
	public void setAdminName(String adminName) {
		AdminName = adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserInfo(String adminName, String adminID) {
		super();
		AdminID = adminID;
		AdminName = adminName;
		
	}
	public UserInfo(String adminName) {
		super();
		AdminName = adminName;
		
	}
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
