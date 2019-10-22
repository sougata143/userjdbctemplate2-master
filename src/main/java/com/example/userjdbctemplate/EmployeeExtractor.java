package com.example.userjdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class EmployeeExtractor implements ResultSetExtractor<Map<String, List<String>>> {

	@Override
	public Map<String, List<String>> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<String, List<String>> employeeMap = new HashMap<>();
		
		while(rs.next()) {
			String firstname = rs.getString("firstname");
			String lastname = rs.getString("lastname");
			String address = rs.getString("address");
			String contact = rs.getString("contact");
			String email = rs.getString("email");
			String gender = rs.getString("gender");
			String department = rs.getString("department");
			String dept_name = rs.getString("dept_name");
			
			List<String> employees = employeeMap.get(dept_name);
			
			if(employees == null) {
				List<String> newEmployees = new ArrayList<>();
				newEmployees.add(firstname);
				newEmployees.add(lastname);
				newEmployees.add(address);
				newEmployees.add(contact);
				newEmployees.add(email);
				newEmployees.add(gender);
				
				employeeMap.put(dept_name, newEmployees);
			}else {
				employees.add(firstname);
				employees.add(lastname);
				employees.add(address);
				employees.add(contact);
				employees.add(email);
				employees.add(gender);
				employees.add(department);
				employees.add(dept_name);
			}
			
			
		}
		
		return employeeMap;
	}

}
