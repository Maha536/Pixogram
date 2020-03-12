package com.pixogram.miscplumbing.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserOutput {
	private Integer id;
	private String username;
	private String fname;
	private String lname;
	private String uemail;
	private String password;
	private LocalDate dob;
	private String profile;

}
