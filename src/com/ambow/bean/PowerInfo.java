package com.ambow.bean;

public class PowerInfo {

	private String adminpopedom;
	private String adminid;
	private String departmentid;
	public String getAdminpopedom() {
		return adminpopedom;
	}
	public void setAdminpopedom(String adminpopedom) {
		this.adminpopedom = adminpopedom;
	}
	public String getAdminid() {
		return adminid;
	}
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public PowerInfo(String adminpopedom, String adminid, String departmentid) {
		super();
		this.adminpopedom = adminpopedom;
		this.adminid = adminid;
		this.departmentid = departmentid;
	}
	public PowerInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
