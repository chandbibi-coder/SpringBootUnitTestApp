package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {

	public Integer saveEmployee(Employee employee);

	public List<Employee> getAllEmployees();

	public Employee getEmployee(Integer id);
	
	public List<String> getEmployeeNames();
	
	public void deleteEmployee(Integer id);
}
