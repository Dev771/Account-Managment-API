package com.nagarro.customermanagmentservice.dto;

import com.nagarro.customermanagmentservice.model.CustomerModel;

public class CustomerDTO {
	
	private String id;
	private String fullName;
	private String userName;
	private String phoneNumber;
	private String email;
	private String gender;
	private String dob;
	private String address;
	private String nationality;
	private String officialDocumentID;
	private String password;
	private boolean authorized;
	private String activatedOn;
	private String createdOn;
	
//	private List<CustomerAccountDetailsEntity> accountList;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public String getOfficialDocumentID() {
		return officialDocumentID;
	}
	public void setOfficialDocumentID(String officialDocumentID) {
		this.officialDocumentID = officialDocumentID;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean getAuthorized() {
		return authorized;
	}
	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}
	
	public String getActivatedOn() {
		return activatedOn;
	}
	public void setActivatedOn(String activatedOn) {
		this.activatedOn = activatedOn;
	}
	
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
//	public List<CustomerAccountDetailsEntity> getAccountList() {
//		return accountList;
//	}
//	public void setAccountList(List<CustomerAccountDetailsEntity> accountList) {
//		this.accountList = accountList;
//	}
	
	public void setCustomerDTO(CustomerModel customerModel) {
		this.id = customerModel.getId();
		this.fullName = customerModel.getFullName();
		this.userName = customerModel.getUserName();
		this.phoneNumber = customerModel.getPhoneNumber();
		this.email = customerModel.getEmail();
		this.gender = customerModel.getGender();
		this.nationality = customerModel.getNationality();
		this.officialDocumentID = customerModel.getOfficialDocumentID();
		this.password = customerModel.getPassword();
		this.dob = customerModel.getDob();
		this.address = customerModel.getAddress();
		this.authorized = customerModel.getAuthorized();
		this.activatedOn = customerModel.getActivatedOn();
		this.createdOn = customerModel.getCreatedOn();
//		this.accountList = customerModel.getAccountList().stream().map(customerAccountModel -> {
//			CustomerAccountDetailsEntity account = new CustomerAccountDetailsEntity();
//			account.setCustomerAccountDetailsEntity(customerAccountModel);
//			return account;
//		}).toList();
	}
	
}
