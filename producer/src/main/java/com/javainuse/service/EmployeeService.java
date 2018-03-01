package com.javainuse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javainuse.dao.EmployeeRepository;
import com.javainuse.model.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee saveEmployee(Employee emp) {
		return employeeRepository.save(emp);
	}
	
	public Employee getEmployee(long empid) {
		return employeeRepository.findOne(empid);
	}
	
	public Iterable<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

}
