package com.pixogram.mediaplumbing.model;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comments {
	
	private Integer id;

	private Integer mediaId;

	private Integer userId;
	
	private String comments;

	private LocalDateTime createdOn;

}
