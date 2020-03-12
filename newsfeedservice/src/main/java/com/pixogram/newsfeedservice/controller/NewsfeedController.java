package com.pixogram.newsfeedservice.controller;

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

import com.pixogram.newsfeedservice.entity.Newsfeed;
import com.pixogram.newsfeedservice.exception.NewsfeedErrorResponse;
import com.pixogram.newsfeedservice.exception.NewsfeedNotFoundException;

import com.pixogram.newsfeedservice.model.NewsFeedList;
import com.pixogram.newsfeedservice.model.NewsFeedModel;
import com.pixogram.newsfeedservice.model.NewsFeedResponse;
import com.pixogram.newsfeedservice.service.INewsfeedService;

@RestController
public class NewsfeedController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private INewsfeedService newsfeedService;
	
	@GetMapping("/newsfeed/{userId}")
	public ResponseEntity<List<String>> getallnews(@PathVariable Integer userId){
		List<String> data = this.newsfeedService.getall(userId);
		ResponseEntity<List<String>> result = new ResponseEntity<List<String>>(data, HttpStatus.OK);
		return result;
	}
	
	@PostMapping("/newsfeed")
	public boolean save(@RequestBody NewsFeedModel newsFeed) {
		this.newsfeedService.save(newsFeed);
		return true;
	}
	
	/*@GetMapping("/newsfeed/{newsfeedId}")
	public ResponseEntity<NewsfeedData> getById(@PathVariable Integer newsfeedId){
		NewsfeedData data = new NewsfeedData();
		Newsfeed record = new Newsfeed();
		Optional<Newsfeed> newsfeed = this.newsfeedService.getWithId(newsfeedId);
		if(newsfeed.isPresent())
			record = newsfeed.get();
		else {
			throw new NewsfeedNotFoundException("newsfeed not found");
		}
		data.setId(record.getId());
		data.setUserId(record.getUserId());
		data.setFeed(record.getFeed());
		data.setCreatedOn(record.getCreatedOn());
		ResponseEntity<NewsfeedData> result = new ResponseEntity<NewsfeedData>(data, HttpStatus.OK);
		return result;
	}
	
	
	//update user
	@PutMapping("/newsfeed")
	public boolean update(@RequestBody NewsfeedData user) {
		this.newsfeedService.updateuser(user);
		return true;
		
	}*/
	
	@ExceptionHandler  // ~catch
	public ResponseEntity<NewsfeedErrorResponse> productOperationErrorHAndler(Exception ex) {
		// create error object
		NewsfeedErrorResponse error = new NewsfeedErrorResponse(ex.getMessage(), 
															  HttpStatus.BAD_REQUEST.value(), 
															  System.currentTimeMillis());
		ResponseEntity<NewsfeedErrorResponse> response =
										new ResponseEntity<NewsfeedErrorResponse>(error, HttpStatus.NOT_FOUND);
		logger.error("Exception :" + error);
		
		return response;
	}
}
