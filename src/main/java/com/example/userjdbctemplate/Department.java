package com.example.userjdbctemplate;

public class Department {

	private int dept_id;
	private int org_id;
	private String deptname;
	
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public int getOrg_id() {
		return org_id;
	}
	public void setOrg_id(int org_id) {
		this.org_id = org_id;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	@Override
	public String toString() {
		return "Department [dept_id=" + dept_id + ", deptname=" + deptname + "]";
	}
	public Department(int dept_id, String deptname) {
		super();
		this.dept_id = dept_id;
		this.deptname = deptname;
	}
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	
}
