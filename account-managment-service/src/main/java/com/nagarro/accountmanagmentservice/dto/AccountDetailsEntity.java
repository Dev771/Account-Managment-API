package com.nagarro.accountmanagmentservice.dto;

import java.math.BigInteger;

import com.nagarro.accountmanagmentservice.model.AccountDetailsModel;

public class AccountDetailsEntity {
	
	private String accountId;
	private String userId;
	private BigInteger balance;
	
//	private List<TransactionEntity> transactionEntityList;

	private String accountType;
	private String createdOn;
	
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public BigInteger getBalance() {
		return balance;
	}
	public void setBalance(BigInteger balance) {
		this.balance = balance;
	}

//	public List<TransactionEntity> getTransactionEntityList() {
//		return transactionEntityList;
//	}
//	public void setTransactionModelList(List<TransactionEntity> transactionEntityList) {
//		this.transactionEntityList = transactionEntityList;
//	}
	
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
	public String toString() {
		return "Account ID: " + accountId 
				+ "\nAccount Type: " + accountType
				+ "\nBalance: " + balance;
	}
	
	public void setAccountDetailsEntity(AccountDetailsModel accountDetailsModel) {
		this.accountId = accountDetailsModel.getAccountId();
		this.accountType = accountDetailsModel.getAccountType();
		this.balance = accountDetailsModel.getBalance();
		this.createdOn = accountDetailsModel.getCreatedOn();
		this.userId = accountDetailsModel.getUserId();
//		this.transactionEntityList = Optional.ofNullable(accountDetailsModel.getTransactionModelList())
//										.map(transactionModelList -> transactionModelList.stream().map(transactionModel -> {						
//												TransactionEntity transactionEntity = new TransactionEntity();
//												transactionEntity.setTransactionEntity(transactionModel);
//												return transactionEntity;
//											}).toList()
//										).orElse(null);
	}
	
	
}
