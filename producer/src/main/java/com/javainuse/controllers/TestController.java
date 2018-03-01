package com.javainuse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.javainuse.model.Employee;
import com.javainuse.service.EmployeeService;

@RestController
public class TestController {
	
	@Autowired
	private EmployeeService employeeService;

	/**
	 * 
	 * @return ResponseEntity<Iterable<Employee>>
	 */
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Employee>> firstPage() {
		return ResponseEntity.ok(employeeService.getAllEmployees());
	}
	
	/**
	 * 
	 * @param emp
	 * @param ucBuilder
	 * @return org.springframework.http.ResponseEntity
	 */
	@PostMapping(value = "/employee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp, UriComponentsBuilder ucBuilder){
		Employee resultantEmp = employeeService.saveEmployee(emp);
		if(resultantEmp != null) {
			HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/employee/{id}").buildAndExpand(resultantEmp.getEmpId()).toUri());
	        return new ResponseEntity<Employee>(resultantEmp, headers, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Employee>(null, null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	/**
	 * 
	 * @param empid
	 * @return org.springframework.http.ResponseEntity
	 */
	@GetMapping(value="/employee/{empid}")
	public ResponseEntity<Employee> getEmployeeWithId(@PathVariable("empid") long empid){
		Employee emp = employeeService.getEmployee(empid);
		return ResponseEntity.ok(emp);
	}

}