package com.zensar.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zensar.dto.Employee;
import com.zensar.service.EmployeeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "emp")
public class SpringDemoController {

	@Autowired
	public EmployeeService employeeService;

	@Autowired
	public RestTemplate restTemplate;
	
	public static Logger logger= LoggerFactory.getLogger(SpringDemoController.class);

	@HystrixCommand(fallbackMethod = "fallbackcircuitbreakertest")
	@GetMapping(value = "/circuit", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> circuitbreakertest() {
		logger.info("Fallback circuit breaker example!");
		String stringRead = restTemplate.getForObject("http://localhost:1000/cr/read", String.class);
		return new ResponseEntity<String>(stringRead, HttpStatus.OK);
	}
	
	public ResponseEntity<String> fallbackcircuitbreakertest() {

		return new ResponseEntity<String>("Fallback circuitbreakertest successfull", HttpStatus.OK);
	}

	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get all employee Status")
	public ResponseEntity<Object> getAllEmployees() {
		logger.info("Get all employee Status starting!");
		return employeeService.getAllEmployees();
	}

	// get emp by query param
	@GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get employee by ID")
	public ResponseEntity<Employee> getEmpById(@RequestParam("id") Integer empId) {
		logger.info("Get employee by ID!");
		return employeeService.getEmpById(empId);
	}

	@PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Create New employee")
	public ResponseEntity<Object> createNewStock(@Valid @RequestBody Employee emp) {
		logger.info("Create New employee!");
		return employeeService.createNewStock(emp);

	}

//	http://localhost:8788/emp/update/1
	@PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Update employee")
	public ResponseEntity<String> updateEmp(@PathVariable("id") int id, @RequestBody Employee newEmp) {
		logger.info("Update employee!");
		return employeeService.updateEmp(id, newEmp);

	}

	// delete emp by id using PathVariable
	@DeleteMapping(value = "/delete/{id}")
	@ApiOperation(value = "Delete employee")
	public ResponseEntity<String> deleteEmpById(@PathVariable("id") int id) {
		logger.info("Delete employee!");
		return employeeService.deleteEmpById(id);
	}

}
