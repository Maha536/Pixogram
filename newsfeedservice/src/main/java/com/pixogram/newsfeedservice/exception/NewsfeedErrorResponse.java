package com.pixogram.newsfeedservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewsfeedErrorResponse {

	private String message;
	private Integer errorCode;
	private Long timeStamp;
	
	
	
	
}
