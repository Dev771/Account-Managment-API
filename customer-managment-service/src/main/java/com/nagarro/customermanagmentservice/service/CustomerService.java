package com.nagarro.customermanagmentservice.service;

import java.util.List;

import com.nagarro.customermanagmentservice.dto.CustomerDTO;
import com.nagarro.customermanagmentservice.dto.HeaderResponse;

public interface CustomerService {
	public HeaderResponse addCustomer(CustomerDTO customerDTO);
	public List<CustomerDTO> getAllCustomers();
	public HeaderResponse getCustomerDetails(String id);
	public HeaderResponse updateCustomer(CustomerDTO customerDTO);
	public HeaderResponse deleteCustomer(String id, String token);
}
