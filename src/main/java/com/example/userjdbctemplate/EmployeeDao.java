package com.example.userjdbctemplate;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDao {
	
	@Autowired
	DataSource dataSource;
	
	@Transactional
	public void createTable() {
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		String sql = "create table employee (id int, firstname varchar(50), lastname varchar(50), address varchar(50), contact varchar(50), email varchar(50), gender varchar(50))";
		try {
			//jt.execute(sql);
			jt.execute(sql);
			System.out.println("table user2 created");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Transactional 
	public String saveTableData(int id,String firstname, String lastname, String address, String contact, String email, String gender) {
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		String sql = "insert into employee (id, firstname, lastname, address, contact, email, gender) "
				+ "values ('"+id+"','"+firstname+"','"+lastname+"','"+address+"','"+contact+"','"+email+"','"+gender+"')";
		try {
//			jt.execute(sql);
			jt.execute(sql);
			System.out.println("data inserted successfully");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ("data inserted "+firstname+" "+lastname+" "+address+" "+contact+" "+email+" "+gender);
	}
	
	
	@Transactional
	public void updateTableData(String firstname, String lastname, String address, String contact, String email, String gender, int id) {
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		String sql = "update employee set firstname = ?, lastname = ?, address = ?, contact = ?, email = ?, gender = ? where id = ?";
		try {
			jt.update(sql, firstname,lastname,address,contact,email,gender,id);
			System.out.println("updated successfully");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Transactional
	public void deleteTableData(int id) {
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		String sql = "delete from employee e where e.id = "+id+"";
		try {
			jt.execute(sql);
			System.out.println("user deleted");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void getTableData(){
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		String sql = "select * from employee";
		List<Map<String, Object>> employees = jt.queryForList(sql);
		if(employees!=null && !employees.isEmpty()) {
			for(Map<String, Object> employee : employees) {
				for(Iterator<Map.Entry<String, Object>> it = employee.entrySet().iterator(); it.hasNext();) {
					Map.Entry<String, Object> entry = it.next();
					String key = entry.getKey();
					Object value = entry.getValue();
					System.out.println(key+" = "+value);
				}
				System.out.println();
			}
		}
	}
	
	@Transactional
	public void createTableWithCustomName(String name) {
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		String sql = "create table " +name+ "(id int, firstname varchar(50), lastname varchar(50), address varchar(50), "
				+ "contact varchar(50), email varchar(50), gender varchar(50))";
		try {
			//jt.execute(sql);
			jt.execute(sql);
			System.out.println("table " +name+ " created");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/*@Transactional
	public List<Employee> getAllEmployeeRest(){
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		//String sql = "select * from employee";
		String sql = "select emp.id, emp.firstname, emp.lastname, emp.email, "
				+ "emp.address, emp.contact, emp.gender, emp.department, dept.dept_name from employee emp, department dept";
			List<Employee> employees = jt.query(sql, new BeanPropertyRowMapper(Employee.class));
			return employees;
		
	}*/
	
	/*@Transactional
	public List<Employee> getAllEmployeeRest(){
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		//String sql = "select * from employee";
		String sql = "select emp.id, emp.firstname, emp.lastname, emp.email, "
				+ "emp.address, emp.contact, emp.gender, emp.department, dept.dept_name from employee emp, department dept where emp.department=dept.dept_id";
			List<Employee> employees = jt.query(sql, new EmployeeMapper());
			return employees;
		
	}*/
	
	/*@Transactional
	public List<Employee> getAllEmployeeRest(){
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		//String sql = "select * from employee";
		String sql = "select emp.id, emp.firstname, emp.lastname, emp.email, "
				+ "emp.address, emp.contact, emp.gender, emp.department, dept.dept_name from employee emp,"
				+ " department dept where emp.department=dept.dept_id";
			List<Employee> employees = jt.query(sql, new BeanPropertyRowMapper(Employee.class));
			return employees;
		
	}*/
	
	@Transactional
	public List<Employee> getAllEmployeeRest(){
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		//String sql = "select * from employee";
		String sql = "select * from employee inner join department on employee.department = department.dept_id";
			List<Employee> employees = jt.query(sql, new BeanPropertyRowMapper(Employee.class));
			return employees;
		
	}
	
	@Transactional
	public Map<String, List<String>> getAllEmployeeByDepartmentRest(){
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		//String sql = "select * from employee";
		String sql = "select emp.id, emp.firstname, emp.lastname, emp.email, "
				+ "emp.address, emp.contact, emp.gender, emp.department, dept.dept_name from employee emp, department dept where emp.department=dept.dept_id";
			Map<String, List<String>> employees = jt.query(sql, new EmployeeExtractor());
			return employees;
		
	}
	
	
	@Transactional
	public List<Employee> getAllEmployeeRestWithDepartment(String firstname){
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		String sql = "select emp.id, emp.firstname, emp.lastname, emp.email, emp.address, emp.contact, dept.dept_name,"
				+ " emp.gender, emp.department, dept.dept_name"
				+ " from employee emp, department dept where emp.firstname = ? and emp.department=dept.dept_id";
		//List<Employee> employees = jt.query(sql, new Object[] {firstname} ,new EmployeeMapper());
		List<Employee> employees = jt.query(sql, new Object[] {firstname} ,new BeanPropertyRowMapper(Employee.class));
		return employees;
		
	}
	
	@Transactional
	public List<Employee> getAllEmployeeRestWithDepartmentById(int id){
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		String sql = "select emp.id, emp.firstname, emp.lastname, emp.email, emp.address, emp.contact, dept.dept_name,"
				+ " emp.gender, emp.department, dept.dept_name"
				+ " from employee emp, department dept where emp.id = ? and emp.department=dept.dept_id";
		//List<Employee> employees = jt.query(sql, new Object[] {firstname} ,new EmployeeMapper());
		List<Employee> employees = jt.query(sql, new Object[] {id} ,new BeanPropertyRowMapper(Employee.class));
		return employees;
		
	}
	
	@Transactional 
	public String saveTableDataBody(Employee employee) {
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		String sql = "insert into employee (id, firstname, lastname, address, contact, email, gender, department) "
				+ "values ('"+employee.getId()+"','"+employee.getFirstname()+"','"+employee.getLastname()+"'"
						+ ",'"+employee.getAddress()+"','"+employee.getContact()+"','"+employee.getEmail()+"'"
								+ ",'"+employee.getGender()+"','"+employee.getDepartment()+"')";
		try {
//			jt.execute(sql);
			jt.execute(sql);
			System.out.println("data inserted successfully");
			System.out.println("data inserted "+employee.getFirstname()+" "+employee.getLastname()+" "+employee.getAddress()+""
					+ " "+employee.getContact()+" "+employee.getEmail()+" "+employee.getGender());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return ("data inserted "+employee.getFirstname()+" "+employee.getLastname()+" "+employee.getAddress()+" "
				+ ""+employee.getContact()+" "+employee.getEmail()+" "+employee.getGender());
	}
	

}
