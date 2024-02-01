package com.ToDoList.ToDoList.services;

import java.util.List;

import com.ToDoList.ToDoList.entities.Role;
import com.ToDoList.ToDoList.entities.Roles;
import com.ToDoList.ToDoList.entities.User;

public interface RoleService {

	List<Roles> getAllRoles();
	
	Roles removeAllUserFromRole(int roleId);
	
	User removeUserFromRole(int userId,int roleId);
	
	User assignUserToRole(int userId,int roleId);
	
}
