package com.nagarro.accountmanagmentservice.model;

import java.math.BigInteger;

import com.nagarro.accountmanagmentservice.dto.TransactionEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TransactionModel {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String transactionId;
	private String transactionType;
//	@JoinColumn(name = "senderAccountId")
//	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private String senderAccountId;
	private String recieverAccountId;
	private BigInteger amount;
	private String createdOn;
	
	
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
	
	public void setTransactionModel(TransactionEntity transactionEntity) {
		this.transactionId = transactionEntity.getTransactionId();
		this.senderAccountId = transactionEntity.getSenderAccountId();
		this.recieverAccountId = transactionEntity.getRecieverAccountId();
		this.amount = transactionEntity.getAmount();
		this.createdOn = transactionEntity.getCreatedOn();
		this.transactionType = transactionEntity.getTransactionType();
	}
	
}
