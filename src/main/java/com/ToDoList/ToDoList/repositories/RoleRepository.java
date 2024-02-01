package com.ToDoList.ToDoList.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ToDoList.ToDoList.entities.Roles;

public interface RoleRepository  extends JpaRepository<Roles,Integer>{

	Optional <Roles> findByName(String string);

}
