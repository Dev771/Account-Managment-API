package com.nagarro.customermanagmentservice.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.net.HttpHeaders;
import com.nagarro.customermanagmentservice.dto.CustomerDTO;
import com.nagarro.customermanagmentservice.dto.HeaderResponse;
import com.nagarro.customermanagmentservice.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@PostMapping("/add")
	public HeaderResponse addCustomer(@RequestBody CustomerDTO customerDto) {
		return customerService.addCustomer(customerDto);
	}
	
	@GetMapping
	public List<CustomerDTO> getAllCustomers() {
		return customerService.getAllCustomers();
	}
	
	
	@GetMapping("/getCustomer")
	public ResponseEntity<HeaderResponse> getCustomer(
		@PathParam("id") String id
	) {
		HeaderResponse headerResponse = customerService.getCustomerDetails(id);
		if(headerResponse.isStatus()) {
			return new ResponseEntity<HeaderResponse>(headerResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<HeaderResponse>(headerResponse, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping
	public HeaderResponse updateCustomer(@RequestBody CustomerDTO customerDTO) {
		return customerService.updateCustomer(customerDTO);
	}
	
	@DeleteMapping("/{id}")
	public HeaderResponse deleteCustomer(@PathVariable("id") String id, HttpServletRequest request) {
		return customerService.deleteCustomer(id, request.getHeader(HttpHeaders.AUTHORIZATION));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String exceptionHandler() {
		return "Enter Valid Credentials!!!";
	}
	
}
