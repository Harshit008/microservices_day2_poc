package com.zensar.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Employee")
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Range(min=0, max=90)
	@ApiModelProperty("Employee Id")
	private Integer id;

	@NotBlank
	@ApiModelProperty("Employee Name")
	private String empName;
	
	@NotBlank
	@ApiModelProperty("Employee Role")
	private String role;
	
	public Employee(int id, String empName, String role) {
		super();
		this.id = id;
		this.empName = empName;
		this.role = role;
	}
	public Employee() {
		
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", empName=" + empName + ", salary=" + role + "]";
	}


	
}
