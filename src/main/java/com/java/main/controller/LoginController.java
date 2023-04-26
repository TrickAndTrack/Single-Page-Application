package com.java.main.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.java.main.entity.Users;
import com.java.main.service.RegisterService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

	@Autowired
	private RegisterService service;

	@GetMapping(value = "/all")
	public ResponseEntity<List<Users>> getAll() {
		try {

			return ResponseEntity.ok(service.getAllUsers());
		} catch (HttpClientErrorException ex) {

			return ResponseEntity.status(ex.getStatusCode()).build();
		} catch (HttpServerErrorException ex) {

			return ResponseEntity.status(ex.getStatusCode()).build();
		} catch (Exception ex) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping(value = "/secured/login")
	public Principal user(Principal principal) {
		return principal;
	}

	@PostMapping(value = "/register")
	public ResponseEntity<Users> saveUsers(@RequestBody Users users) {
		try {
			return ResponseEntity.ok(service.saveUsers(users));
		} catch (HttpClientErrorException ex) {

			return ResponseEntity.status(ex.getStatusCode()).build();
		} catch (HttpServerErrorException ex) {

			return ResponseEntity.status(ex.getStatusCode()).build();
		} catch (Exception ex) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/user/{Id}")
	public ResponseEntity<Optional<Users>> getProfile(@PathVariable Integer Id) {
		try {
			Optional<Users> user = service.getUser(Id);
			return ResponseEntity.ok(user);
		} catch (HttpClientErrorException ex) {

			return ResponseEntity.status(ex.getStatusCode()).build();
		} catch (HttpServerErrorException ex) {

			return ResponseEntity.status(ex.getStatusCode()).build();
		} catch (Exception ex) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	 @PutMapping("/{id}")
	    public ResponseEntity<Users> updateEmployee(@PathVariable Integer id, @RequestBody Users users) {
	        Optional<Users> optionalEmployee = service.getUser(id);
	        if (!optionalEmployee.isPresent()) {
	            return ResponseEntity.notFound().build();
	        }
	 
	        users.setRegisterId(id);
	        service.saveUsers(users);
	 
	        return ResponseEntity.ok(users);
	    }
	 @PutMapping("/{id}/verified")
	    public ResponseEntity<String> updateProductAvailability(@PathVariable Integer id, @RequestParam boolean verified) {
		 service.updateUserVerified(id, verified);
	        return ResponseEntity.ok("User verified updated successfully");
	    }
	}
	 
	 
	 
//	    @PatchMapping("/{id}")
//	    public ResponseEntity<Users> patchEmployee(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
//	        Optional<Users> optionalEmployee = service.getUser(id);
//	        if (!optionalEmployee.isPresent()) {
//	            return ResponseEntity.notFound().build();
//	        }
//	 
//	        Users users = optionalEmployee.get();
//	 
//	        for (String key : updates.keySet()) {
//	            switch (key) {
//	                case "name":
//	                	users.setName((String) updates.get(key));
//	                    break;
//	                case "email":
//	                	users.setEmail((String) updates.get(key));
//	                    break;
//	            }
//	        }
//	 
//	        service.saveUsers(users);
//	 
//	        return ResponseEntity.ok(users);
//	    }
	

