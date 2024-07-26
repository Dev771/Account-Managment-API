package com.nagarro.customerauthorization.service;

import com.nagarro.customerauthorization.dto.HeaderResponse;
import com.nagarro.customerauthorization.dto.UserModel;

public interface AuthService {
	public void registerUser(UserModel userModel) throws IllegalArgumentException;

	public HeaderResponse validateToken(String token);
	
	public HeaderResponse deleteUser(String email);
}
