package com.pixogram.followservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pixogram.followservice.entity.Follow;
import com.pixogram.followservice.model.FollowData;
import com.pixogram.followservice.model.FollowList;
import com.pixogram.followservice.model.FollowModel;
import com.pixogram.followservice.repository.FollowRepository;


@RestController
public class FollowController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FollowRepository followRepository;
	
	/*@Autowired
	private IFollowService followService;
	*/
	@GetMapping("/check-followings/mine/{mineId}/other/{otherId}")
	public ResponseEntity<Boolean> getFollowingStatus(@PathVariable Integer mineId, @PathVariable Integer otherId){
		
		Boolean status = this.followRepository.checkFollowing(mineId, otherId);
		ResponseEntity<Boolean> result = new ResponseEntity<Boolean>(status,HttpStatus.OK);
		return result;
		
	}
	//to get following list
	@GetMapping("following/{mineId}")
	public ResponseEntity<List<Integer>> getFollowingList(@PathVariable Integer mineId){
		List<Integer> list = this.followRepository.getFollowingList(mineId);
		
		ResponseEntity<List<Integer>> result = new ResponseEntity<List<Integer>>(list, HttpStatus.OK);
		return result;	
	}
	
	//to get followers list
	@GetMapping("followers/{mineId}")
	public ResponseEntity<List<Integer>> getFollowersList(@PathVariable Integer mineId){
		List<Integer> list = this.followRepository.getFollowersList(mineId);
		ResponseEntity<List<Integer>> result = new ResponseEntity<List<Integer>>(list, HttpStatus.OK);
		return result;	
	}
	/*
	@GetMapping("/follow")
	public ResponseEntity<FollowModel> getallfollows(){
		FollowModel data = new FollowModel();
		data.setFollowlist(this.followService.getall());
		ResponseEntity<FollowModel> result = new ResponseEntity<FollowModel>(data,HttpStatus.OK);
		return result;
		
	}
	*/
	
	//start following 
	@PostMapping("/follow")
	public Boolean follow(@RequestBody Follow follow)
	{
		Boolean status =this.followRepository.follow(follow);
		return status;
	}
	
	//make unfollow
	@DeleteMapping("/unfollow/mine/{mineId}/other/{otherId}")
	public Boolean unfollow(@PathVariable Integer mineId, @PathVariable Integer otherId)
	{	
		Boolean status = this.followRepository.unFollow(mineId, otherId);
		return status;
	}
	

}
