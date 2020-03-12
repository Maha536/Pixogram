package com.pixogram.userservice.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UserInput {
	
	private String username;
	private String fname;
	private String lname;
	private String uemail;
	private String password;
	private LocalDate dob;
	private String profile;
	
}
