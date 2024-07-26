package com.nagarro.customermanagmentservice.dto;

public class JwtResponse {

	private String jwtToken;
	private String username;
	
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
