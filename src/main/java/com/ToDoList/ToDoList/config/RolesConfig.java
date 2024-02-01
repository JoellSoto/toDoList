package com.ToDoList.ToDoList.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.ToDoList.ToDoList.entities.Role;
import com.ToDoList.ToDoList.entities.Roles;
import com.ToDoList.ToDoList.entities.User;
import com.ToDoList.ToDoList.repositories.RoleRepository;
import com.ToDoList.ToDoList.repositories.UserRepository;

@Configuration
public class RolesConfig {

	
	@Bean
	CommandLineRunner  comandLinerRunnerRoles(RoleRepository roleRepository) {
		long numberRoles= roleRepository.count(); 
		List<Roles> roles=new ArrayList<Roles>();
		
		if(numberRoles<=0)
			return args ->{
				Roles adminRole =new Roles();
				adminRole.setName(Role.ADMIN.name());
				roles.add(adminRole);
				
				Roles userRole =new Roles();
				userRole.setName(Role.USER.name());
				roles.add(userRole);
				
				roleRepository.saveAll(roles);
				
				Roles role=new Roles();
				User user= new User();
				role=roleRepository.findByName("ADMIN").get();
				
				user.setEmail("Example@email.com");
				user.setFirstName("Admin");
				user.setSecondName("Admin");
				user.setRoles(Collections.singletonList(role));
				user.setPassword( new BCryptPasswordEncoder().encode("Admin"));
			};
		return null;
		
	}
}
