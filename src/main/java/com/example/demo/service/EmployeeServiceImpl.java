package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository emRepository;

	@Override
	public Integer saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		Employee e= emRepository.save(employee);
		return e.getEmpId();
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return emRepository.findAll();
	}

	@Override
	public Employee getEmployee(Integer id) {
		// TODO Auto-generated method stub
		return emRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("101","employee not found"));
		
		/* Optional<Employee> emp=   emRepository.findById(id);
		if(emp.isPresent()) 
			return emp.get();
		else
			throw new EmployeeNotFoundException("101","employee not found"); */
	}

	@Override
	public List<String> getEmployeeNames() {
		// TODO Auto-generated method stub
		return  emRepository.getEmployeeNames();
	}

	@Override
	public void deleteEmployee(Integer id) {
		// TODO Auto-generated method stub
		 Employee e=  getEmployee(id);
		 emRepository.delete(e);
	}
}
