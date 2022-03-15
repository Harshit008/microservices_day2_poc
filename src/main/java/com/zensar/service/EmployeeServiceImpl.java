package com.zensar.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zensar.dto.Employee;
import com.zensar.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	public EmployeeRepository empRepo;
	
	@Override
	public ResponseEntity<Object> getAllEmployees() {
		return new ResponseEntity<>(empRepo.findAll(), HttpStatus.OK);  
	}

	@Override
	public ResponseEntity<Employee> getEmpById(Integer empId) {
		return new ResponseEntity<Employee>(empRepo.findById(empId).get(), HttpStatus.OK);  
	}

	@Override
	public ResponseEntity<Object> createNewStock(Employee emp) {
		
	    return new ResponseEntity<>(empRepo.saveAndFlush(emp), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> updateEmp(Integer id, Employee newEmp) {
		Employee oldEmp = empRepo.findById(id).get();
		oldEmp.setEmpName(newEmp.getEmpName());
		oldEmp.setRole(newEmp.getRole());
		
		empRepo.saveAndFlush(oldEmp);
		
	    return new ResponseEntity<>("Employee updated successfully", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteEmpById(Integer id) {
		
		empRepo.deleteById(id);
	    return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);

	}

		

}
