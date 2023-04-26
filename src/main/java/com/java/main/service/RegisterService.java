package com.java.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}
	
	public Optional<Users> getUser(Integer registerId) {
		Optional<Users> user = userRepository.findById(registerId);
		return user;

	}
	public void deleteUserById(Integer registerId) {
		userRepository.deleteById(registerId);
    }
	
	public void updateUserVerified(Integer registerId, boolean verified) {
        Optional<Users> optionalUser = userRepository.findById(registerId);
        if (optionalUser.isPresent()) {
        	Users users = optionalUser.get();
        	users.setVerified(verified);
        	userRepository.save(users);
        }
    }

}
