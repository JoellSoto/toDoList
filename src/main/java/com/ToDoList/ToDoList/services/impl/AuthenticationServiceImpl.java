package com.ToDoList.ToDoList.services.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ToDoList.ToDoList.dto.JwtAuthenticationResponse;
import com.ToDoList.ToDoList.dto.RefreshTokenRequest;
import com.ToDoList.ToDoList.dto.SignInRequest;
import com.ToDoList.ToDoList.dto.SignUpRequest;
import com.ToDoList.ToDoList.entities.Role;
import com.ToDoList.ToDoList.entities.Roles;
import com.ToDoList.ToDoList.entities.User;
import com.ToDoList.ToDoList.exceptions.ResourceNotFoundException;
import com.ToDoList.ToDoList.repositories.RoleRepository;
import com.ToDoList.ToDoList.repositories.UserRepository;
import com.ToDoList.ToDoList.services.AuthenticationService;
import com.ToDoList.ToDoList.services.JWTService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JWTService jwtService;
	private final RoleRepository roleRepository;
	
	@Autowired
	public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager, JWTService jwtService, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.roleRepository = roleRepository;
	
	}
	

	public User signUp(SignUpRequest signUpRequest) {
		Roles role=new Roles();
		User user= new User();
		role=roleRepository.findByName("USER").get();
		
		
		user.setEmail(signUpRequest.getEmail());
		user.setFirstName(signUpRequest.getFirstName());
		user.setSecondName(signUpRequest.getLastName());
		user.setRoles(Collections.singletonList(role));
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		
		return userRepository.save(user);
	}
	
	public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));
		} catch (Exception e) {
			throw new ResourceNotFoundException("System Login",HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.name() , "Incorrect Username/Password!");
		}
		
		var user=userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(()->new IllegalArgumentException("Ivalid email or password"));
		var acessToken= jwtService.generateToken(user);
		var refreshToken= jwtService.generateRefreshToken(new HashMap<>(), user);
		
		JwtAuthenticationResponse jwtAuthenticationResponse= new JwtAuthenticationResponse();
		jwtAuthenticationResponse.setToken(acessToken);
		jwtAuthenticationResponse.setRefreshToken(refreshToken);
		
		return jwtAuthenticationResponse;
	}
	
	public JwtAuthenticationResponse refreshToken(RefreshTokenRequest RefreshTokenRequest) {
		String userEmail = jwtService.extractUserName(RefreshTokenRequest.getToken());
		User user = userRepository.findByEmail(userEmail).orElseThrow();
		if(jwtService.isTokenValid(RefreshTokenRequest.getToken(), user)) {
			var acessToken= jwtService.generateToken(user);
			
			JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
			jwtAuthenticationResponse.setToken(acessToken);
			jwtAuthenticationResponse.setRefreshToken(RefreshTokenRequest.getToken());
			return jwtAuthenticationResponse;
		}
		return null;
	}
	
}
