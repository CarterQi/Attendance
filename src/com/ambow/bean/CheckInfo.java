package com.ambow.bean;

public class CheckInfo {

	private String employeename;
	private String cardnumber;
	private String departmentname;
	private String attendancememo;
	private String noteid;
	private String adminid;
	private String adminname;
	private String typeid;
	private String typename;
	private String flag;
	private String date;
	private String employeeid;
	
	public String getNoteid() {
		return noteid;
	}
	public void setNoteid(String noteid) {
		this.noteid = noteid;
	}
	public String getAdminid() {
		return adminid;
	}
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getFlag() {
	
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getDate() {
		return date;
	}
	public void setData(String date) {
		this.date = date;
	}
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public String getEmployeename() {
		return employeename;
	}
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public String getAttendancememo() {
		return attendancememo;
	}
	public void setAttendancememo(String attendancememo) {
		this.attendancememo = attendancememo;
	}
	public CheckInfo(String employeename, String cardnumber, String departmentname,
			String attendancememo,String adminname,String date,String typename,String noteid,String flag) {
		
		this.employeename = employeename;
		this.cardnumber = cardnumber;
		this.departmentname = departmentname;
		this.attendancememo = attendancememo;
		this.adminname=adminname;
		this.date=date;
		this.typename=typename;
		this.noteid=noteid;
		this.flag=flag;
	}
	public CheckInfo(String employeename,String cardnumber,String typename,String noteid,String departmentname,String attendancememo) {
		this.typename=typename;
		this.noteid=noteid;
		this.employeename = employeename;
		this.cardnumber = cardnumber;
		this.departmentname = departmentname;
		this.attendancememo = attendancememo;
	}
	public CheckInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
