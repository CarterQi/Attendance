package com.ambow.bean;

public class LeaveInfo {

	private String typeid;
	private String typename;
	private String typecategory;
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
	public String getTypecategory() {
		return typecategory;
	}
	public void setTypecategory(String typecategory) {
		this.typecategory = typecategory;
	}
	public LeaveInfo(String typeid, String typename, String typecategory) {
		super();
		this.typeid = typeid;
		this.typename = typename;
		this.typecategory = typecategory;
	}
	public LeaveInfo(String typeid, String typename) {
		this.typeid = typeid;
		this.typename = typename;
	}
	public LeaveInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
