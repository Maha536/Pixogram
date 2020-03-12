package com.pixogram.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData {
	private String message;
	private Long timeStamp;
	private String profile;
	private String firstname;
	private String lastname;
	private Integer id;
}
