package com.pixogram.userservice.controller;


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

import com.pixogram.userservice.service.AuthoritiesService;
import com.pixogram.userservice.service.IUserServices;
import com.pixogram.userservice.entity.Users;
import com.pixogram.userservice.exception.UserErrorResponse;
import com.pixogram.userservice.exception.UserNotFoundException;
import com.pixogram.userservice.model.CommentsResponse;
import com.pixogram.userservice.model.DataModel;
import com.pixogram.userservice.model.ProfileData;
import com.pixogram.userservice.model.SearchedUserModelList;
import com.pixogram.userservice.model.UserInput;
import com.pixogram.userservice.model.UserOutput;
import com.pixogram.userservice.model.Userid;
import com.pixogram.userservice.service.Userservices;

@RestController
public class UsersController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IUserServices userServices;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@GetMapping("/search-users/{searchText}")
	public ResponseEntity<SearchedUserModelList> getSearchedUsers(@PathVariable String searchText){
		SearchedUserModelList list =  this.userServices.searchUsers(searchText);
		ResponseEntity<SearchedUserModelList> result = new ResponseEntity<SearchedUserModelList>(list, HttpStatus.OK);
		return result;
	}
	
	@GetMapping("/users")
	public ResponseEntity<DataModel> getall(){
		DataModel data = new DataModel();
		data.setUsers(this.userServices.getall());
		ResponseEntity<DataModel> result = new ResponseEntity<DataModel>(data, HttpStatus.OK);
		return result;
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<UserOutput> getById(@PathVariable Integer userId){
		UserOutput user = new UserOutput();
		Users record = new Users();
		Optional<Users> users = this.userServices.getWithId(userId);
		if(users.isPresent())
			record = users.get();
		else {
			throw new UserNotFoundException("User not found");
		}
		user.setId(record.getId());
		user.setUsername(record.getUserName());
		user.setDob(record.getDob());
		user.setFname(record.getFirstName());
		user.setLname(record.getLastName());
		user.setPassword(record.getPassword());
		user.setUemail(record.getEmail());
		user.setProfile(record.getProfile());
		ResponseEntity<UserOutput> result = new ResponseEntity<UserOutput>(user, HttpStatus.OK);
		return result;
	}
	
	//save new user
	@PostMapping("/users")
	public boolean save(@RequestBody UserOutput user) {
		// this.userServices.saveuser(user);
		return true;
	}
	
	//to get usernames already exist
	@GetMapping("/custom/{username}")
	public ResponseEntity<Userid> getuserid(@PathVariable String username) {
		Integer id ;
		try {
		
		Userid userid = this.userServices.getUserId(username);
		ResponseEntity<Userid> result = new ResponseEntity<Userid>(userid, HttpStatus.OK);
		return result;
		}
		catch(Exception e) {
			throw new UserNotFoundException("Id not found in repository");
		}
		
	}
	
	//update user
	@PutMapping("/users/{Userid}")
	public boolean update(@PathVariable Integer Userid,@RequestBody UserInput user) {
		UserOutput u =new  UserOutput();
		u.setId(Userid);
		u.setUsername(user.getUsername());
		u.setFname(user.getFname());
		u.setLname(user.getLname());
		u.setDob(user.getDob());
		u.setPassword(user.getPassword());
		u.setProfile(user.getProfile());
		u.setUemail(user.getUemail());
		this.userServices.updateuser(u);
		return true;
		
	}
	//get details of user to display the profile
	@GetMapping("/profile/{userId}")
	public ResponseEntity<ProfileData> getProfileById(@PathVariable Integer userId){
		ProfileData user = new ProfileData();
		Users record = new Users();
		Optional<Users> users = this.userServices.getWithId(userId);
		if(users.isPresent())
			record = users.get();
		else {
			throw new UserNotFoundException("User not found");
		}
		user.setUserid(record.getId());
		user.setUsername(record.getUserName());
		user.setName(record.getFirstName()+" "+record.getLastName());
		user.setProfile(record.getProfile());
		ResponseEntity<ProfileData> result = new ResponseEntity<ProfileData>(user, HttpStatus.OK);
		return result;
	}
	
	@GetMapping("/comments/{userId}")
	public ResponseEntity<String> getCommentedUsername(@PathVariable Integer userId){
		
		String name = this.userServices.getCommentedUsername(userId);
		ResponseEntity<String> result = 
					new ResponseEntity<String>(name,HttpStatus.OK);
		return result;
		
	}
	
	@ExceptionHandler  // ~catch
	public ResponseEntity<UserErrorResponse> productOperationErrorHAndler(Exception ex) {
		// create error object
		UserErrorResponse error = new UserErrorResponse(ex.getMessage(), 
															  HttpStatus.BAD_REQUEST.value(), 
															  System.currentTimeMillis());
		ResponseEntity<UserErrorResponse> response =
										new ResponseEntity<UserErrorResponse>(error, HttpStatus.NOT_FOUND);
		logger.error("Exception :" + error);
		
		return response;
	}


}
