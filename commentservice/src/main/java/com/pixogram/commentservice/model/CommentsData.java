package com.pixogram.commentservice.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentsData {
	
	private Integer mediaId;
	private Integer userId;
	private String comments;
	
}
