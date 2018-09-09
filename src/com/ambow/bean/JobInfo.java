package com.ambow.bean;

public class JobInfo {

	private String positionid;
	private String positionname;
	
	
	public JobInfo(String positionid, String positionname) {
		super();
		this.positionid = positionid;
		this.positionname = positionname;
	}


	public String getPositionid() {
		return positionid;
	}


	public void setPositionid(String positionid) {
		this.positionid = positionid;
	}


	public String getPositionname() {
		return positionname;
	}


	public void setPositionname(String positionname) {
		this.positionname = positionname;
	}


	public JobInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
