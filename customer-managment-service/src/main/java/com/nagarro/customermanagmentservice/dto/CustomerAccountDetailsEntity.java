package com.nagarro.customermanagmentservice.dto;

//import com.nagarro.customermanagmentservice.model.CustomerAccountDetailModel;

public class CustomerAccountDetailsEntity {

	private String relationId;
	
	private String userId;
	private String accountId;
	private String createdAt;
	
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public String toString() {
		return "Account ID: " + accountId + "\n User ID: " + userId;
	}
	
//	public void setCustomerAccountDetailsEntity(CustomerAccountDetailModel accountDetailModel) {
//		this.relationId = accountDetailModel.getRelationId();
//		this.accountId = accountDetailModel.getAccountId();
//		this.userId = accountDetailModel.getUserId();
//		this.createdAt = accountDetailModel.getCreatedAt();
//	}
	
}
