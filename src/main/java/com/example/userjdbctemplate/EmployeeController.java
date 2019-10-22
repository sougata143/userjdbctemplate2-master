package com.example.userjdbctemplate;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	DepartmentDao departmentDao;
	
	@PostMapping("/createtable")
	public void createTable() {
		employeeDao.createTable();
	}
	
	/*@PostMapping("/insertdata")
	public void insertData() {
		employeeDao.saveTableData();
	}*/
	
	@PostMapping("/save/{id}/{firstname}/{lastname}/{address}/{contact}/{email}/{gender}")
	public void saveEmployee(@PathVariable("id") int id, @PathVariable("firstname") String firstname,
			@PathVariable("lastname") String lastname, @PathVariable("address") String address, @PathVariable("contact") String contact,
			@PathVariable("email") String email, @PathVariable("gender") String gender) {
		employeeDao.saveTableData(id, firstname, lastname, address, contact, email, gender);
	}
	
	@DeleteMapping("/deletedata/{id}")
	public void deleteDataByFirstname(@PathVariable("id") int id) {
		employeeDao.deleteTableData(id);
	}
	
	@GetMapping("/")
	public void getTableData() {
		employeeDao.getTableData();
	}
	
	@PostMapping("/createcustomtable/{name}")
	public void createCustomTable(@PathVariable("name") String name) {
		employeeDao.createTableWithCustomName(name);
	}
	
	@PutMapping("/update/{firstname}/{lastname}/{address}/{contact}/{email}/{gender}/{id}")
	public void updateEmployee(@PathVariable("firstname") String firstname,
			@PathVariable("lastname") String lastname, @PathVariable("address") String address, @PathVariable("contact") String contact,
			@PathVariable("email") String email, @PathVariable("gender") String gender, @PathVariable("id") int id) {
		employeeDao.updateTableData(firstname, lastname, address, contact, email, gender, id);
	}
	
	
	@GetMapping("/rest")
	public List<Employee> getTableDataRest() {
		return employeeDao.getAllEmployeeRest();
	}
	
	@GetMapping("/restdepartment")
	public Map<String, List<String>> getTableDataRest1() {
		return employeeDao.getAllEmployeeByDepartmentRest();
	}
	
	@GetMapping("/getEmployeeByFirstname/{firstname}")
	public List<Employee> getTableDataByFirstname(@PathVariable("firstname") String firstname){
		return employeeDao.getAllEmployeeRestWithDepartment(firstname);
	}
	
	@GetMapping("/getEmployeeById/{id}")
	public List<Employee> getTableDataById(@PathVariable("id") int id){
		return employeeDao.getAllEmployeeRestWithDepartmentById(id);
	}
	
	@GetMapping("/departments")
	public List<Department> getDepartments(){
		return departmentDao.getAllDepartments();
	}
	
	@PostMapping("/save/")
	public void saveEmployeeBody(@RequestBody Employee employee) {
		employeeDao.saveTableDataBody(employee);
	}
	

}
