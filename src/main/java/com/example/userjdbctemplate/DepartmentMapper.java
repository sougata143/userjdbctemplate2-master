package com.example.userjdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

public class DepartmentMapper implements RowMapper<Department>{

	@Override
	public Department mapRow(ResultSet arg0, int arg1) throws SQLException {
		Department department = new Department();
		department.setDept_id(arg0.getInt("dept_id"));
		department.setOrg_id(arg0.getInt("org_id"));
		department.setDeptname(arg0.getString("dept_name"));
		return department;
	}

	

}
