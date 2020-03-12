package com.pixogram.commentservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentErrorResponse {

	private String message;
	private Integer errorCode;
	private Long timeStamp;
	
	
	
	
}
