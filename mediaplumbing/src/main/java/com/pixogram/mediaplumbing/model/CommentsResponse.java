package com.pixogram.mediaplumbing.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentsResponse {
	
	private String userName;
	private String comments;
	private String createdOn;
}
