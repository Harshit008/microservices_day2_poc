package com.zensar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Employee Id Not Found")
public class InvalidEmpIdException extends RuntimeException {

	private String message;

	public InvalidEmpIdException() {
		this.message = "";
	}

	public InvalidEmpIdException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "Invalid Id =" + message + "";
	}
	
	

}
