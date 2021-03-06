package com.drivercar.democar.domain.config;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.drivercar.democar.domain.model.User;
import com.drivercar.democar.domain.repository.UserRepository;

@Configuration
public class LoadUserConfig {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserRepository userRepository;
	
	@PostConstruct
	public void init() {
		User admin = new User();
		admin.setPassword(passwordEncoder.encode("password"));
		admin.setRoles(Arrays.asList("ROLE_ADMIN"));
		admin.setUsername("admin");
		admin.setEnabled(true);
		
		userRepository.save(admin);
	}

}
