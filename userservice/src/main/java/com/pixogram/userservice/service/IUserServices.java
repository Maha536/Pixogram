package com.pixogram.userservice.service;

import java.util.List;
import java.util.Optional;

import com.pixogram.userservice.entity.Users;
import com.pixogram.userservice.model.SearchedUserModelList;
import com.pixogram.userservice.model.UserInput;
import com.pixogram.userservice.model.UserOutput;
import com.pixogram.userservice.model.Userid;

public interface IUserServices {
	
	public List<Users> getall();
	public void saveuser(UserInput userInput);
	public Optional<Users> getWithId(Integer id);
	public void updateuser(UserOutput action);
	public Userid getUserId(String username);
	public List<String> getUsernames();
	public void deleteauth(String username);
	public Integer getLikesByMediaId(Integer id);
	public SearchedUserModelList searchUsers(String searchString);
	public String getCommentedUsername(Integer userId);
	
}
