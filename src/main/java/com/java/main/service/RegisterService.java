package com.java.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.main.config.UserConfig;
import com.java.main.entity.Users;
import com.java.main.repository.UserRepository;

@Service
public class RegisterService {

	@Autowired
	private UserRepository userRepository;

	public Users saveUsers(Users users) {
		String password=UserConfig.getPassword(users.getPwd());
		users.setPwd(password); 
		
		return userRepository.save(users);
	}

}
