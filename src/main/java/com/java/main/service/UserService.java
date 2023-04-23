package com.java.main.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java.main.entity.Users;
import com.java.main.repository.UserRepository;


@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users users = userRepository.findByEmail(email);
		GrantedAuthority authority = new SimpleGrantedAuthority(users.getRole());
		User user = new User(users.getEmail(), users.getPwd(), Arrays.asList(authority));
		UserDetails details = (UserDetails) user;
		return details;
	}

}
