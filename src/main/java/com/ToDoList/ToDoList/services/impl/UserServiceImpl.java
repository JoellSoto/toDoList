package com.ToDoList.ToDoList.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ToDoList.ToDoList.repositories.UserRepository;
import com.ToDoList.ToDoList.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{


	private final UserRepository userRepository;
	
	@Autowired
	UserServiceImpl(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	public UserDetailsService userDetailsService() {
		
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String userName) {
				return userRepository.findByEmail(userName).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
			}
		};
	}
}

