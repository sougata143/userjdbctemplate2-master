package com.example.userjdbctemplate;

import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;import org.springframework.validation.BeanPropertyBindingResult;

@Component
public class DepartmentDao {
	
	@Autowired
	DataSource ds;
	
	@Transactional
	public List<Department> getAllDepartments(){
		JdbcTemplate jt = new JdbcTemplate(ds);
		String sql = "select * from department dept";
		List<Department> departments = jt.query(sql, new DepartmentMapper());
		return departments;
	}

}
