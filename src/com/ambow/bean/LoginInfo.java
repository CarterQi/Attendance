package com.ambow.bean;

public class LoginInfo {
	private int AdminID;
	private String AdminName;
	private String password;
	public int getAdminID() {
		return AdminID;
	}
	public void setAdminID(int adminID) {
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
	public LoginInfo(int adminID, String adminName, String password) {
		super();
		AdminID = adminID;
		AdminName = adminName;
		this.password = password;
	}
	public LoginInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
