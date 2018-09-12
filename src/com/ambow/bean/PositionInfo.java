package com.ambow.bean;

public class PositionInfo {

	private String positionid;
	private String positionname;
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
	public PositionInfo(String positionid, String positionname) {
		super();
		this.positionid = positionid;
		this.positionname = positionname;
	}
	public PositionInfo(String positionname) {
		this.positionname = positionname;
	}
	public PositionInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
