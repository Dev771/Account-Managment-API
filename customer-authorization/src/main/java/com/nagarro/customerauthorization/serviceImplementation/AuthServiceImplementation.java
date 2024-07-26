package com.nagarro.customerauthorization.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;

import com.nagarro.customerauthorization.dto.HeaderResponse;
import com.nagarro.customerauthorization.dto.UserModel;
import com.nagarro.customerauthorization.model.UserEntity;
import com.nagarro.customerauthorization.repositry.UserRepositry;
import com.nagarro.customerauthorization.security.JwtHelper;
import com.nagarro.customerauthorization.service.AuthService;

@Service
public class AuthServiceImplementation implements AuthService, UserDetailsService {

	@Autowired
	private UserRepositry userRepositry;

	@Autowired
	private JwtHelper jwtHelper;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepositry.findById(username).orElse(null);
		if(user != null) {
			UserModel userModel = new UserModel();
			userModel.setUserModel(user);
	
			return userModel;
		} else {
			throw new UsernameNotFoundException("User Doesn't Exist Try Again With Valid Credentials!!!");
		}
	}
	
	@Override
	public void registerUser(UserModel userModel) throws IllegalArgumentException {
		
		System.out.println(userModel.getEmail());
		
		UserEntity userEntity = new UserEntity();
		userEntity.setUserEntity(userModel);
		
		UserEntity checkUser = userRepositry.findById(userEntity.getEmail()).orElse(null);
		
		if(checkUser != null) {
			throw new IllegalArgumentException("User Already Exists!!!!");
		}
		
		userRepositry.save(userEntity);
		
	}


	@Override
	public HeaderResponse validateToken(String token) {
		HeaderResponse headerResponse = new HeaderResponse();
		
		
		if(jwtHelper.validateToken(token)) {
			headerResponse.setStatus(true);
			
			System.out.println(jwtHelper.getUsernameFromToken(token));
			
			headerResponse.setMsg(jwtHelper.getUsernameFromToken(token));
		} else {
			headerResponse.setStatus(false);
			headerResponse.setMsg("Invalid Token!!");
		}

		
		return headerResponse;
	}
	
	@Override
	public HeaderResponse deleteUser(String email) {
		
		UserEntity userEntity = userRepositry.findById(email).orElse(null);
		
		HeaderResponse headerResponse = new HeaderResponse();
		
		if(userEntity != null) {
			userRepositry.deleteById(email);
		}
		
		headerResponse.setStatus(true);
		headerResponse.setMsg("User Deleted Successfully!!");
		
		return headerResponse;
	}
	
}
