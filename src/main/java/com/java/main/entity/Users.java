package com.java.main.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users {

	@Id
	@GeneratedValue
    private int registerId;
    private String firstname;
    private String lastname;
    private String dob;
    private String addressLineOne;
    private String addressLineTwo;
    private String pincode;
    private Long phonenumber;
    private String email;
    private String pwd;
    private String Role = "USER";
    private boolean verified;
	
}
