package com.nagarro.customerauthorization.model;

import com.nagarro.customerauthorization.dto.UserModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserEntity {

	@Id
	private String email;
	private String password;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public void setUserEntity(UserModel userModel) {
		this.email = userModel.getEmail();
		this.password = userModel.getPassword();
	}
}
