package com.ambow.bean;

public class EmpInfo {
	private int EmployeeID;
	private String EmployeeName;
	private String EmployeeGender;
	private int PositionID;
	private int DepartmentID;
	private String CardNumber;
	private String EmployeeState;
	private String EmployeeMemo;
	public EmpInfo(int employeeID, String employeeName, String employeeGender, int positionID, int departmentID,
			String cardNumber, String employeeState, String employeeMemo) {
		super();
		EmployeeID = employeeID;
		EmployeeName = employeeName;
		EmployeeGender = employeeGender;
		PositionID = positionID;
		DepartmentID = departmentID;
		CardNumber = cardNumber;
		EmployeeState = employeeState;
		EmployeeMemo = employeeMemo;
	}

	public EmpInfo(String employeeName) {
		this.EmployeeName=employeeName;
	}
	public int getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(int employeeID) {
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

	public int getPositionID() {
		return PositionID;
	}

	public void setPositionID(int positionID) {
		PositionID = positionID;
	}

	public int getDepartmentID() {
		return DepartmentID;
	}

	public void setDepartmentID(int departmentID) {
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