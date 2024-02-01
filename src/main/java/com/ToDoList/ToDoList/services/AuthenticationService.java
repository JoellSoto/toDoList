package com.ToDoList.ToDoList.services;

import com.ToDoList.ToDoList.dto.JwtAuthenticationResponse;
import com.ToDoList.ToDoList.dto.RefreshTokenRequest;
import com.ToDoList.ToDoList.dto.SignInRequest;
import com.ToDoList.ToDoList.dto.SignUpRequest;
import com.ToDoList.ToDoList.entities.User;

public interface AuthenticationService {
 
	public User signUp(SignUpRequest signUpRequest);
	public JwtAuthenticationResponse signIn(SignInRequest signInRequest);
	JwtAuthenticationResponse refreshToken(RefreshTokenRequest RefreshTokenRequest);
}
