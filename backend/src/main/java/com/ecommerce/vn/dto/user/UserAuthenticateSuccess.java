package com.ecommerce.vn.dto.user;

public class UserAuthenticateSuccess {
	
	private String token;
	
	private String type = "Bearer ";

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
