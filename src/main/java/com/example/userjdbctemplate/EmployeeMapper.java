package com.example.userjdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setFirstname(rs.getString("firstname"));
		employee.setLastname(rs.getString("lastname"));
		employee.setAddress(rs.getString("address"));
		employee.setContact(rs.getString("contact"));
		employee.setEmail(rs.getString("email"));
		employee.setGender(rs.getString("gender"));
		employee.setDepartment(rs.getString("department"));
		employee.setDept_name(rs.getString("dept_name"));
		
		return employee;
	}

}
