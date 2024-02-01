package com.ToDoList.ToDoList.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {

	private String token;
	private String refreshToken;
	
	public String getToken() {
		return token;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	
}
