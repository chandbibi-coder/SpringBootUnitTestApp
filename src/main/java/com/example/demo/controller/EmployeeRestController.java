package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

	 public static Logger logger=  LoggerFactory.getLogger(EmployeeRestController.class);
	
	@Autowired
	private EmployeeService empService;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee emplyee){
	   Integer id=	  empService.saveEmployee(emplyee);
	   logger.info("employee saved");
		return ResponseEntity.ok("Employee saved "+ id);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		List<Employee> empL =  empService.getAllEmployees();
		logger.info("got all employee");
		return new ResponseEntity<List<Employee>>(empL, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Integer id){
		Employee emp =  empService.getEmployee(id);
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}
	
	@GetMapping("/names")
	public ResponseEntity<List<String>> getEmployeeNames(){
		List<String>  employeeNames =  empService.getEmployeeNames();
		return new ResponseEntity<List<String>>(employeeNames, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Integer id){
		empService.deleteEmployee(id);
		return new ResponseEntity<String>("deleted", HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee){
		Employee emp =  empService.getEmployee(id);
		emp.setEmpName(employee.getEmpName());
		emp.setEmpSal(employee.getEmpSal());
		
		Integer i = empService.saveEmployee(emp);
		return new ResponseEntity<String>("employee updated", HttpStatus.OK); 
	}
	
}
