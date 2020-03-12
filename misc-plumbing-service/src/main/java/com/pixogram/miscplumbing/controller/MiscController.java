package com.pixogram.miscplumbing.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.pixogram.miscplumbing.feignproxy.FollowServiceProxy;
import com.pixogram.miscplumbing.feignproxy.UserServiceProxy;
import com.pixogram.miscplumbing.model.FollowUserData;
import com.pixogram.miscplumbing.model.FollowUserDataList;
import com.pixogram.miscplumbing.model.SearchedUserModel;
import com.pixogram.miscplumbing.model.SearchedUserModelList;
import com.pixogram.miscplumbing.model.UserOutput;

@CrossOrigin("*")
@RestController
public class MiscController {

	@Autowired
	private UserServiceProxy userServiceProxy;
	
	@Autowired
	private FollowServiceProxy followServiceProxy;
	
	@GetMapping("/searched-users/{searchText}/myid/{userId}")
	public ResponseEntity<SearchedUserModelList> searchUsers(@PathVariable String searchText, @PathVariable Integer userId) {
		// 1 retreived list of searched users
		SearchedUserModelList list =  
				this.userServiceProxy.getSearchedUsers(searchText).getBody();
		
		// 2. loop through all users and find if following
		for(SearchedUserModel user : list.getUserList()) {
			// user.getUserId()
			Boolean status = this.followServiceProxy.getFollowingStatus(userId, user.getUserId()).getBody();
			user.setFollowed(status);
		}
		ResponseEntity<SearchedUserModelList> response =
				new ResponseEntity<SearchedUserModelList>(list, HttpStatus.OK);
		return response;
	}
	/*
	//following list
	@GetMapping("/following/{mineId}")
	public ResponseEntity<List<Integer>> getFollowings(@PathVariable Integer mineId){
		ResponseEntity<List<Integer>> list = this.followServiceProxy.getFollowingList(mineId);
		return list;
	}
	
	//followers list
	@GetMapping("/followers/{mineId}")
	public ResponseEntity<List<Integer>> getFollowers(@PathVariable Integer mineId){
		ResponseEntity<List<Integer>> list = this.followServiceProxy.getFollowersList(mineId);
		return list;
	}
	*/
	//following list
		@GetMapping("/following/{mineId}")
		public ResponseEntity<FollowUserDataList> getFollowings(@PathVariable Integer mineId){
			List<FollowUserData> followdata = 
					(List<FollowUserData>) this.followServiceProxy.getFollowingList(mineId).getBody().stream()
			.map((id)->{

				UserOutput user = this.userServiceProxy.getById(id).getBody();
				FollowUserData data = new FollowUserData(id,user.getUsername(),user.getFname()+" "+user.getLname(),user.getProfile());
				return data;
			}).collect(Collectors.toList());
			FollowUserDataList datalist = new FollowUserDataList();
			datalist.setFollowList(followdata);
			ResponseEntity<FollowUserDataList> result = 
										new ResponseEntity<FollowUserDataList>(datalist, HttpStatus.OK);
			return result;
		}
		// to get followers
		//
		@GetMapping("/followers/{mineId}")
		public ResponseEntity<FollowUserDataList> getFollowers(@PathVariable Integer mineId){
			List<FollowUserData> followdata = 
					(List<FollowUserData>) this.followServiceProxy.getFollowersList(mineId).getBody().stream()
			.map((id)->{

				UserOutput user = this.userServiceProxy.getById(id).getBody();
				FollowUserData data = new FollowUserData(id,user.getUsername(),user.getFname()+" "+user.getLname(),user.getProfile());
				return data;
			}).collect(Collectors.toList());
			FollowUserDataList datalist = new FollowUserDataList();
			datalist.setFollowList(followdata);
			ResponseEntity<FollowUserDataList> result = 
										new ResponseEntity<FollowUserDataList>(datalist, HttpStatus.OK);
			return result;
		}
		
		
	
	
}
