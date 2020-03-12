package com.pixogram.commentservice.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pixogram.commentservice.model.CommentsModel;
import com.pixogram.commentservice.service.ICommentsService;
import com.pixogram.commentservice.entity.Comments;
import com.pixogram.commentservice.exception.CommentErrorResponse;
import com.pixogram.commentservice.exception.CommentNotFoundException;
import com.pixogram.commentservice.model.CommentsCountModel;
import com.pixogram.commentservice.model.CommentsData;
import com.pixogram.commentservice.model.CommentsList;

@RestController
public class CommentsController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ICommentsService commentsService;
	
	@GetMapping("/comment")
	public ResponseEntity<CommentsModel> getallcomments(){
		CommentsModel result = new CommentsModel();
		result.setCommentslist(this.commentsService.getall());
		ResponseEntity<CommentsModel> data = new ResponseEntity<CommentsModel>(result, HttpStatus.OK);
		
		return data;
	}
	
	@PostMapping("/comment")
	public boolean save(@RequestBody CommentsData comment) {
		this.commentsService.save(comment);
		return true;
	}
	//Getting comments by media id
	@GetMapping("/commentbymediaid/{mediaId}")
	public ResponseEntity<CommentsList> getCommentsById(@PathVariable Integer mediaId){
		CommentsData data = new CommentsData();
		Comments record = new Comments();
		List<Comments> comments = this.commentsService.getWithId(mediaId);
		
		CommentsList list = new CommentsList();
		list.setCommentsList(comments);
		ResponseEntity<CommentsList> result = new ResponseEntity<CommentsList>(list, HttpStatus.OK);
		return result;
	}
	
	/*
	@PutMapping("/comment")
	public boolean update(@RequestBody CommentsData user) {
		
		this.commentsService.updateuser(user);
		return true;
		
	}
	*/
	
	@GetMapping("/getcount/{mediaid}")
	public ResponseEntity<CommentsCountModel> getCountById(@PathVariable Integer mediaid) {
		CommentsCountModel data = this.commentsService.getCountById(mediaid);
		ResponseEntity<CommentsCountModel> result = new ResponseEntity<CommentsCountModel>(data, HttpStatus.OK);
		return result;
		
	}
	
	@ExceptionHandler  // ~catch
	public ResponseEntity<CommentErrorResponse> productOperationErrorHAndler(Exception ex) {
		// create error object
		CommentErrorResponse error = new CommentErrorResponse(ex.getMessage(), 
															  HttpStatus.BAD_REQUEST.value(), 
															  System.currentTimeMillis());
		ResponseEntity<CommentErrorResponse> response =
										new ResponseEntity<CommentErrorResponse>(error, HttpStatus.NOT_FOUND);
		logger.error("Exception :" + error);
		
		return response;
	}

}
