package com.pixogram.commentservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixogram.commentservice.entity.Comments;
import com.pixogram.commentservice.repository.CommentsRepository;
import com.pixogram.commentservice.repository.CustomRepo;
import com.pixogram.commentservice.model.CommentsCountModel;
import com.pixogram.commentservice.model.CommentsData;

@Service
public class CommentsService implements ICommentsService {
	
	@Autowired
	private CommentsRepository commentsRepository;
	
	@Autowired
	private CustomRepo custom;
	
	public List<Comments> getall(){
		List<Comments> records = this.commentsRepository.findAll();
		return records;
		
	}
	
	public void save(CommentsData comment) {
		Comments data = new Comments();
		data.setComments(comment.getComments());
		data.setUserId(comment.getUserId());
		data.setMediaId(comment.getMediaId());
		this.commentsRepository.save(data);
		
	}
	
	public List<Comments> getWithId(Integer mediaId){
		try {
			List<Comments> record= this.commentsRepository.findByMediaId(mediaId);
			return record;
		}
		catch(Exception e) {
			List<Comments> record= null;
			return record;
		}
		
		
	}
	/*
	public void updateuser(CommentsData comment) {
		Comments data = new Comments();
		data.setId(comment.getId());
		data.setComments(comment.getComments());
		data.setUserId(comment.getUserId());
		data.setMediaId(comment.getMediaId());
		data.setCreatedOn(comment.getCreatedOn());
		this.commentsRepository.save(data);
	}
	*/
	@Override
	public CommentsCountModel getCountById(Integer mediaid) {
		// TODO Auto-generated method stub
		CommentsCountModel data = this.custom.findCountById(mediaid);
		return data;
	}


}
