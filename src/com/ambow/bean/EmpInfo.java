package com.ambow.bean;

public class EmpInfo {
	private String EmployeeID;
	private String EmployeeName;
	private String EmployeeGender;
	private String PositionID;
	private String DepartmentID;
	private String DepartmentName;
	private String PositionName;
	public String getDepartmentName() {
		return DepartmentName;
	}

	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}

	public String getPositionName() {
		return PositionName;
	}

	public void setPositionName(String positionName) {
		PositionName = positionName;
	}



	private String CardNumber;
	private String EmployeeState;
	private String EmployeeMemo;
	public EmpInfo(String employeeID, String employeeName, String employeeGender, String positionName, String departmentName,
			String cardNumber, String employeeState, String employeeMemo) {
		super();
		EmployeeID = employeeID;
		EmployeeName = employeeName;
		EmployeeGender = employeeGender;
		PositionName = positionName;
		DepartmentName = departmentName;
		CardNumber = cardNumber;
		EmployeeState = employeeState;
		EmployeeMemo = employeeMemo;
	}

	public EmpInfo(String employeeName) {
		this.EmployeeName=employeeName;
	}
	public String getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	public String getEmployeeGender() {
		return EmployeeGender;
	}

	public void setEmployeeGender(String employeeGender) {
		EmployeeGender = employeeGender;
	}

	public String getPositionID() {
		return PositionID;
	}

	public void setPositionID(String positionID) {
		PositionID = positionID;
	}

	public String getDepartmentID() {
		return DepartmentID;
	}

	public void setDepartmentID(String departmentID) {
		DepartmentID = departmentID;
	}

	public String getCardNumber() {
		return CardNumber;
	}

	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}

	public String getEmployeeState() {
		return EmployeeState;
	}

	public void setEmployeeState(String employeeState) {
		EmployeeState = employeeState;
	}

	public String getEmployeeMemo() {
		return EmployeeMemo;
	}

	public void setEmployeeMemo(String employeeMemo) {
		EmployeeMemo = employeeMemo;
	}

	
	
	public EmpInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}