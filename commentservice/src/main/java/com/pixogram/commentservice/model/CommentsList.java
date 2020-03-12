package com.pixogram.commentservice.model;

import java.util.List;

import com.pixogram.commentservice.entity.Comments;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentsList {
	private List<Comments> commentsList;
}
