package com.ambow.bean;

public class CheckInfo {

	private String employeename;
	private String cardnumber;
	private String departmentname;
	private String attendancememo;
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
	public CheckInfo(String employeename, String cardnumber, String departmentname, String attendancememo) {
		super();
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
