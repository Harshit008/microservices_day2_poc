package com.zensar.monitoring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import com.zensar.dto.Employee;
import com.zensar.repository.EmployeeRepository;

@Endpoint(id = "database-monitoring")
@Component
public class DbMonitoring {
	
	@Autowired
	private EmployeeRepository repo;
	
	
	
	@ReadOperation
	public Map<String,String> dbMonitoring(){
		Map<String,String> employeeMap=new HashMap<String, String>();
		List<Employee> employeeList = repo.findAll();
		Integer employeeSize = employeeList.size();
		employeeMap.put("Total Employee records consumed to database", employeeSize.toString());
		return employeeMap;
		
	}
}
