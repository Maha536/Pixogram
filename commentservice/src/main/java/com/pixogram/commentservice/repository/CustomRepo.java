package com.pixogram.commentservice.repository;

import com.pixogram.commentservice.model.CommentsCountModel;

public interface CustomRepo {
	
	public CommentsCountModel findCountById(Integer mediaid);
}
