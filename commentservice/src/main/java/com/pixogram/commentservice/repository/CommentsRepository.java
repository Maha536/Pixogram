package com.pixogram.commentservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pixogram.commentservice.entity.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {
	
	public List<Comments> findByMediaId(Integer mediaId);
}
