package com.zensar.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.zensar.dto.Employee;

public interface EmployeeService {

	public ResponseEntity<Object> getAllEmployees();
	public ResponseEntity<Employee> getEmpById(@RequestParam("id") Integer empId); 
	public ResponseEntity<Object> createNewStock(@Valid @RequestBody Employee emp);
	public ResponseEntity<String> updateEmp(@PathVariable("id") Integer id, @RequestBody Employee newEmp);
	public ResponseEntity<String> deleteEmpById(@PathVariable("id") Integer id ) ;
}
