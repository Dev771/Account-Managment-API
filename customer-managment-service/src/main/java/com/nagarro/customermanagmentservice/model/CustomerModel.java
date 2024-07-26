package com.nagarro.customermanagmentservice.model;

import com.nagarro.customermanagmentservice.dto.CustomerDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CustomerModel {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
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
	
//	private List<CustomerAccountDetailModel> accountList;
	
	
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
	
//	public List<CustomerAccountDetailModel> getAccountList() {
//		return accountList;
//	}
//	public void setAccountList(List<CustomerAccountDetailModel> accountList) {
//		this.accountList = accountList;
//	}
	
	public void updateCustomerModel(CustomerModel customerModel) {
		this.fullName = customerModel.getFullName() == null ? this.fullName : customerModel.getFullName();
		this.userName = customerModel.getUserName() == null ? this.userName : customerModel.getUserName();
		this.phoneNumber = customerModel.getPhoneNumber() == null ? this.phoneNumber : customerModel.getPhoneNumber();
		this.gender = customerModel.getGender() == null ? this.gender : customerModel.getGender();
		this.nationality = customerModel.getNationality() == null ? this.nationality : customerModel.getNationality();
		this.officialDocumentID = customerModel.getOfficialDocumentID() == null ? this.officialDocumentID : customerModel.getOfficialDocumentID();
		this.password = customerModel.getPassword() == null ? this.password : customerModel.getPassword();
		this.dob = customerModel.getDob() == null ? this.dob : customerModel.getDob();
		this.address = customerModel.getAddress() == null ? this.address : customerModel.getAddress();
	}
	
	public void setCustomerModel(CustomerDTO customerDTO) {
		this.id = customerDTO.getId();
		this.fullName = customerDTO.getFullName();
		this.userName = customerDTO.getUserName();
		this.phoneNumber = customerDTO.getPhoneNumber();
		this.email = customerDTO.getEmail();
		this.gender = customerDTO.getGender();
		this.nationality = customerDTO.getNationality();
		this.officialDocumentID = customerDTO.getOfficialDocumentID();
		this.password = customerDTO.getPassword();
		this.dob = customerDTO.getDob();
		this.address = customerDTO.getAddress();
		this.authorized = customerDTO.getAuthorized();
		this.activatedOn = customerDTO.getActivatedOn();
		this.createdOn = customerDTO.getCreatedOn();
//		this.accountList = customerDTO.getAccountList().stream().map(customerAccountDetailEnity -> {
//			CustomerAccountDetailModel accountDetailModel = new CustomerAccountDetailModel();
//			accountDetailModel.setCustomerAccountDetailsModel(customerAccountDetailEnity);
//			
//			return accountDetailModel;
//		}).toList();
	}
	
}
