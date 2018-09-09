package com.ambow.bean;

public class DeptInfo {

	private String departmentid;
	private String departmentname;
	private String starttimeam;
	private String endtimeam;
	private String starttimepm;
	private String endtimepm;
	private String parentid;
	
	public DeptInfo(String departmentid, String departmentname, String starttimeam, String endtimeam, String starttimepm,
			String endtimepm, String parentid) {
		super();
		this.departmentid = departmentid;
		this.departmentname = departmentname;
		this.starttimeam = starttimeam;
		this.endtimeam = endtimeam;
		this.starttimepm = starttimepm;
		this.endtimepm = endtimepm;
		this.parentid = parentid;
	}
 
	public DeptInfo(String departmentname,String parentid,String departmentid) {
		this.departmentname=departmentname;
		this.parentid=parentid;
		this.departmentid=departmentid;
	}
	public DeptInfo(String departmentname) {
		this.departmentname=departmentname;
	}
	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getStarttimeam() {
		return starttimeam;
	}

	public void setStarttimeam(String starttimeam) {
		this.starttimeam = starttimeam;
	}

	public String getEndtimeam() {
		return endtimeam;
	}

	public void setEndtimeam(String endtimeam) {
		this.endtimeam = endtimeam;
	}

	public String getStarttimepm() {
		return starttimepm;
	}

	public void setStarttimepm(String starttimepm) {
		this.starttimepm = starttimepm;
	}

	public String getEndtimepm() {
		return endtimepm;
	}

	public void setEndtimepm(String endtimepm) {
		this.endtimepm = endtimepm;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public DeptInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
