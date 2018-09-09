package com.ambow.bean;

public class NotesInfo {

	private String noteid;
	private String employeeid;
	private String employeename;
	private String operatorname;
	private String typename;
	private String notetypeid;
	private String cause;
	private String fillintime;
	private String directorsign;
	private String administrationsign;
	private String persidentsign;
	private String startdate;
	private String enddate;
	private String starttime;
	private String endtime;
	private String projectname;
	private String adminid;
	private String notememo;
	private String operatorid;
	
	public String getEmployeename() {
		return employeename;
	}
	public NotesInfo(String noteid,String fillintime,String cause,String adminid,String employeename, String operatorname, String typename,
			 String startdate,
			 String starttime, String endtime) {
		super();
		this.noteid = noteid;
		this.employeename = employeename;
		this.operatorname = operatorname;
		this.typename = typename;
		this.startdate = startdate;
		this.starttime = starttime;
		this.endtime = endtime;
	}
	public NotesInfo(String noteid, String employeeid, String employeename, String operatorname, String typename,
			String notetypeid, String cause, String fillintime, String directorsign, String administrationsign,
			String persidentsign, String startdate, String enddate, String starttime, String endtime,
			String projectname, String adminid, String notememo, String operatorid) {
		super();
		this.noteid = noteid;
		this.employeeid = employeeid;
		this.employeename = employeename;
		this.operatorname = operatorname;
		this.typename = typename;
		this.notetypeid = notetypeid;
		this.cause = cause;
		this.fillintime = fillintime;
		this.directorsign = directorsign;
		this.administrationsign = administrationsign;
		this.persidentsign = persidentsign;
		this.startdate = startdate;
		this.enddate = enddate;
		this.starttime = starttime;
		this.endtime = endtime;
		this.projectname = projectname;
		this.adminid = adminid;
		this.notememo = notememo;
		this.operatorid = operatorid;
	}
	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}
	public String getOperatorname() {
		return operatorname;
	}
	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getNoteid() {
		return noteid;
	}
	public void setNoteid(String noteid) {
		this.noteid = noteid;
	}
	public String getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(String employeeid) {
		this.employeeid = employeeid;
	}
	public String getNotetypeid() {
		return notetypeid;
	}
	public void setNotetypeid(String notetypeid) {
		this.notetypeid = notetypeid;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getFillintime() {
		return fillintime;
	}
	public void setFillintime(String fillintime) {
		this.fillintime = fillintime;
	}
	public String getDirectorsign() {
		return directorsign;
	}
	public void setDirectorsign(String directorsign) {
		this.directorsign = directorsign;
	}
	public String getAdministrationsign() {
		return administrationsign;
	}
	public void setAdministrationsign(String administrationsign) {
		this.administrationsign = administrationsign;
	}
	public String getPersidentsign() {
		return persidentsign;
	}
	public void setPersidentsign(String persidentsign) {
		this.persidentsign = persidentsign;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getAdminid() {
		return adminid;
	}
	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}
	public String getNotememo() {
		return notememo;
	}
	public void setNotememo(String notememo) {
		this.notememo = notememo;
	}
	public String getOperatorid() {
		return operatorid;
	}
	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}
	public NotesInfo(String noteid, String employeeid, String notetypeid, String cause, String fillintime,
			String directorsign, String administrationsign, String persidentsign, String startdate, String enddate,
			String starttime, String endtime, String projectname, String adminid, String notememo, String operatorid) {
		super();
		this.noteid = noteid;
		this.employeeid = employeeid;
		this.notetypeid = notetypeid;
		this.cause = cause;
		this.fillintime = fillintime;
		this.directorsign = directorsign;
		this.administrationsign = administrationsign;
		this.persidentsign = persidentsign;
		this.startdate = startdate;
		this.enddate = enddate;
		this.starttime = starttime;
		this.endtime = endtime;
		this.projectname = projectname;
		this.adminid = adminid;
		this.notememo = notememo;
		this.operatorid = operatorid;
	}
	public NotesInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
