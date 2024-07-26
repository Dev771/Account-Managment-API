package com.nagarro.apigateway.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResController {

	
	@ExceptionHandler(RuntimeException.class)
	public String exceptionHandler() {
		return "UnAuthorized Access";
	}
	
}
