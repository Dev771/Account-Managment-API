package com.nagarro.accountmanagmentservice.model;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.nagarro.accountmanagmentservice.dto.AccountDetailsEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class AccountDetailsModel {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String accountId;
	private String userId;
	private BigInteger balance;
	
//	@OneToMany(mappedBy = "senderAccountId", cascade = CascadeType.ALL)
//	private List<TransactionModel> transactionModelList;

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

//	public List<TransactionModel> getTransactionModelList() {
//		return transactionModelList;
//	}
//	public void setTransactionModelList(List<TransactionModel> transactionModelList) {
//		this.transactionModelList = transactionModelList;
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
	
	public void setAccountingDetailModel(AccountDetailsEntity accountDetailsEntity) {
		this.accountId = accountDetailsEntity.getAccountId();
		this.accountType = accountDetailsEntity.getAccountType();
		this.balance = accountDetailsEntity.getBalance();
		this.createdOn = accountDetailsEntity.getCreatedOn();
		this.userId = accountDetailsEntity.getUserId();
//		this.transactionModelList = Optional.ofNullable(accountDetailsEntity.getTransactionEntityList())
//										.map(transactionEntityList -> transactionEntityList.stream().map(transactionEntity -> {												
//												TransactionModel transactionModel = new TransactionModel();
//												transactionModel.setTransactionModel(transactionEntity);
//												return transactionModel;
//											}).toList()
//										).orElse(null);
	}
	
}
