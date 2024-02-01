package com.ToDoList.ToDoList.controller;

import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ToDoList.ToDoList.entities.Roles;
import com.ToDoList.ToDoList.entities.User;
import com.ToDoList.ToDoList.services.RoleService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/roles")
@RestController
@RequiredArgsConstructor
public class RolesController {

	private final RoleService roleService;
	
	public RolesController(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Roles>> getAllRoles(){
		return  ResponseEntity.ok(roleService.getAllRoles());
	}
	
	@PostMapping("/remove-all-users-from-role/{id}")
	public Roles removeAllUsersFromRole(@PathVariable("id") int roleId) {
		return roleService.removeAllUserFromRole(roleId);
	}
	
	@PostMapping("/remove-user-from-role")
	public ResponseEntity<User>removeUserToRole(@PathParam("userId") int userId, @PathParam("roleId") int roleId){
		return ResponseEntity.ok(roleService.removeUserFromRole(userId, roleId));
	}
	
	@PostMapping("/assign-user-to-role")
	public ResponseEntity<User>assignUserToRole(@PathParam("userId") int userId, @PathParam("roleId") int roleId){
		return ResponseEntity.ok(roleService.assignUserToRole(userId, roleId));
	}
}
