package com.ToDoList.ToDoList.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name= "ROLE")
public class Roles {


	@Id
	@SequenceGenerator(
			name="Role_sequence",
			sequenceName="Role_sequence",
			allocationSize =1
			)
	
	@GeneratedValue(
			strategy= GenerationType.SEQUENCE,
			generator ="Role_sequence"
			)
	private int id;
	
	@NaturalId
	private String name;
	
	@JsonBackReference(value="users")
	@ManyToMany(mappedBy="roles")
	private Collection <User> users=new HashSet<>();

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void removeAllUsersFromRole() {
		if(getUsers()!= null) {
			List<User>usersInRole = getUsers().stream().toList();
			usersInRole.forEach(this::removeUserFromRole);
		}
	}
	
	public void removeUserFromRole(User user) {
		user.getRoles().remove(this);
		this.getUsers().remove(user);
	}
	
	public void addUserFromRole(User user) {
		user.getRoles().add(this);
		this.getUsers().add(user);
	}
	
}
