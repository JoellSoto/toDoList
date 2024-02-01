package com.ToDoList.ToDoList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ToDoList.ToDoList.dto.JwtAuthenticationResponse;
import com.ToDoList.ToDoList.dto.RefreshTokenRequest;
import com.ToDoList.ToDoList.dto.SignInRequest;
import com.ToDoList.ToDoList.dto.SignUpRequest;
import com.ToDoList.ToDoList.entities.User;
import com.ToDoList.ToDoList.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	@Autowired
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest){
		return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest){
		return ResponseEntity.ok(authenticationService.signIn(signInRequest));
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<JwtAuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
		return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
	}

	
}
