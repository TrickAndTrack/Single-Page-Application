package com.java.main.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.main.entity.Users;
import com.java.main.service.RegisterService;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
	
	
	
	@Autowired
	private RegisterService service;




	@GetMapping(value = "/secured/login")
	public Principal user(Principal principal) {
		return principal;
	}
	
	@PostMapping(value = "/register")
	public Users saveUsers(@RequestBody Users users)
	{
		return service.saveUsers(users); 
	}

}
