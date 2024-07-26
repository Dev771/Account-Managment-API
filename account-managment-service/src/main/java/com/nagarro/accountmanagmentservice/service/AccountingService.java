package com.nagarro.accountmanagmentservice.service;

import com.nagarro.accountmanagmentservice.dto.AccountDetailsEntity;
import com.nagarro.accountmanagmentservice.dto.HeaderResponse;
import com.nagarro.accountmanagmentservice.dto.TransactionEntity;

public interface AccountingService {
	public HeaderResponse addAccount(AccountDetailsEntity accountDetailsEntity, String token);
	public HeaderResponse transferMoney(TransactionEntity transactionEntity);
	public HeaderResponse getAccountDetails(String accountId);
	public HeaderResponse deleteAccountDetails(String accountId);
	public HeaderResponse deleteAccountByEmailId(String emailId);
}
