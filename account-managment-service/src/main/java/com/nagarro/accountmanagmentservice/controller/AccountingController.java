package com.nagarro.accountmanagmentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.net.HttpHeaders;
import com.nagarro.accountmanagmentservice.dto.AccountDetailsEntity;
import com.nagarro.accountmanagmentservice.dto.HeaderResponse;
import com.nagarro.accountmanagmentservice.dto.TransactionEntity;
import com.nagarro.accountmanagmentservice.service.AccountingService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/accounts")
public class AccountingController {

	@Autowired
	private AccountingService accountingService;
	
	@PostMapping("/addAccount")
	public HeaderResponse addAccount(@RequestBody AccountDetailsEntity accountDetailsEntity, HttpServletRequest request) {
		System.out.println(request.getHeader(HttpHeaders.AUTHORIZATION));
		return accountingService.addAccount(accountDetailsEntity, request.getHeader(HttpHeaders.AUTHORIZATION));
	}
	
	@PutMapping("/transferMoney")
	public HeaderResponse addMoney(@RequestBody TransactionEntity transactionEntity) {
		return accountingService.transferMoney(transactionEntity);
	}
	
	@GetMapping("/{accountId}")
	public HeaderResponse getAccountDetails(@PathVariable String accountId) {
		System.out.println(accountId);
		return accountingService.getAccountDetails(accountId);
	}
	
	@DeleteMapping("/{accountId}")
	public HeaderResponse deleteAcocunt(@PathVariable String accountId) {
		return accountingService.deleteAccountDetails(accountId);
	}

	@DeleteMapping("/user/{userId}")
	public ResponseEntity<HeaderResponse> deleteAccountByEmailId(@PathVariable String userId) {
		HeaderResponse headerResponse = accountingService.deleteAccountByEmailId(userId);
		if(headerResponse.isStatus()) {
			return new ResponseEntity<HeaderResponse>(headerResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<HeaderResponse>(headerResponse, HttpStatus.NOT_FOUND);
		}
	}

}
