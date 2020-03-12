package com.pixogram.commentservice.service;

import java.util.List;
import java.util.Optional;

import com.pixogram.commentservice.entity.Comments;
import com.pixogram.commentservice.model.CommentsCountModel;
import com.pixogram.commentservice.model.CommentsData;

public interface ICommentsService {
	
	public List<Comments> getall();
	public void save(CommentsData action);
	public List<Comments> getWithId(Integer id);
	//public void updateuser(CommentsData action);
	public CommentsCountModel getCountById(Integer mediaid);
}
