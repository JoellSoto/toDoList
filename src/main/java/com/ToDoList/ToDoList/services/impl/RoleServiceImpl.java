package com.ToDoList.ToDoList.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ToDoList.ToDoList.entities.Role;
import com.ToDoList.ToDoList.entities.Roles;
import com.ToDoList.ToDoList.entities.User;
import com.ToDoList.ToDoList.repositories.RoleRepository;
import com.ToDoList.ToDoList.repositories.UserRepository;
import com.ToDoList.ToDoList.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	
	@Autowired
	RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository){
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
	}

	
	public List<Roles> getAllRoles() {
		
		return roleRepository.findAll();
	}

	
	public Roles removeAllUserFromRole(int roleId) {
		Optional<Roles> role= roleRepository.findById(roleId);
		role.ifPresent(Roles::removeAllUsersFromRole);
		return roleRepository.save(role.get());
	}


	public User removeUserFromRole(int userId, int roleId) {
		Optional<User> user= userRepository.findById(userId);
		Optional<Roles> role=roleRepository.findById(roleId);
		
		if(role.isPresent() && role.get().getUsers().contains(user.get())){
			role.get().removeUserFromRole(user.get());
			roleRepository.save(role.get());
			return user.get();
		}
		throw new UsernameNotFoundException("User Not Found");
	}


	public User assignUserToRole(int userId, int roleId) {
		Optional<User> user= userRepository.findById(userId);
		Optional<Roles> role=roleRepository.findById(roleId);
		
		if(user.isPresent() && user.get().getRoles().contains(role.get())) 
			throw new UsernameNotFoundException(user.get().getFirstName()+" already is a "+role.get().getName());
		role.ifPresent(theRole->theRole.addUserFromRole(user.get()));
		roleRepository.save(role.get());
		return user.get();
		
	}

}
