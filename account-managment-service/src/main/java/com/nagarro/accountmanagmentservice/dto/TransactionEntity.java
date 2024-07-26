package com.nagarro.accountmanagmentservice.dto;

import java.math.BigInteger;

import com.nagarro.accountmanagmentservice.model.TransactionModel;

public class TransactionEntity {
	private String transactionId;
	private String senderAccountId;
	private String recieverAccountId;
	private BigInteger amount;
	private String createdOn;
	//Either Cash Or Transfer
	private String transactionType;
	
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	public String getSenderAccountId() {
		return senderAccountId;
	}
	public void setSenderAccountId(String senderAccountId) {
		this.senderAccountId = senderAccountId;
	}
	
	public String getRecieverAccountId() {
		return recieverAccountId;
	}
	public void setRecieverAccount(String recieverAccountId) {
		this.recieverAccountId = recieverAccountId;
	}
	
	public BigInteger getAmount() {
		return amount;
	}
	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}
	
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	public void setTransactionEntity(TransactionModel transactionModel) {
		this.transactionId = transactionModel.getTransactionId();
		this.senderAccountId = transactionModel.getSenderAccountId();
		this.recieverAccountId = transactionModel.getRecieverAccountId();
		this.amount = transactionModel.getAmount();
		this.createdOn = transactionModel.getCreatedOn();
		this.transactionType = transactionModel.getTransactionType();
	}
	
}
