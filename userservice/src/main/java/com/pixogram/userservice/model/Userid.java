package com.pixogram.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Userid {
	
	public Integer id;
	public String firstname;
	public String lastname;
	public String profile;

}
