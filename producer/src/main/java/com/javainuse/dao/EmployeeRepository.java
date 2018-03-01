package com.javainuse.dao;

import org.springframework.data.repository.CrudRepository;

import com.javainuse.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {

}
