package com.nagarro.customerauthorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.customerauthorization.dto.HeaderResponse;
import com.nagarro.customerauthorization.dto.JwtRequest;
import com.nagarro.customerauthorization.dto.JwtResponse;
import com.nagarro.customerauthorization.dto.UserModel;
import com.nagarro.customerauthorization.security.JwtHelper;
import com.nagarro.customerauthorization.service.AuthService;

@RestController
@RequestMapping("/auth/Customer")
@CrossOrigin
public class Authcontroller {
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthService authService;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/Validate/{token}")
	public HeaderResponse validateToken(@PathVariable String token) {
		System.out.println(token);
		return authService.validateToken(token);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

		if(request.getType() != null && request.getType().equalsIgnoreCase("register")) {
			
			UserModel userModel = new UserModel();
			userModel.setEmail(request.getEmail());
			userModel.setPassword(passwordEncoder.encode(request.getPassword()));
			
			authService.registerUser(userModel);
		}
		
		this.doAuthenticate(request.getEmail(), request.getPassword());
		System.out.println(request.getEmail());
		

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
		System.out.println(userDetails);
		String token = this.jwtHelper.generateToken(userDetails);
		System.out.println(request.getPassword());
		
		System.out.println(token);
		
		JwtResponse response = new JwtResponse();
		response.setJwtToken(token);
		response.setUsername(userDetails.getUsername());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{email}")
	private HeaderResponse deleteUser(@PathVariable("email") String email) {
		return authService.deleteUser(email);
	}


	private void doAuthenticate(String email, String password) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
		try {			
			authenticationManager.authenticate(token);
		} catch(BadCredentialsException e) {
			throw new BadCredentialsException("Invalid Username or Password!!!");
		}
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	private String exceptionHandler() {
		return "Invalid Credentials!!";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String illegalExceptionHandler() {
		return "User Already Exists";
	}
}
