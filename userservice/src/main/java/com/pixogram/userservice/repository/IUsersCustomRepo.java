package com.pixogram.userservice.repository;

import java.util.List;

import com.pixogram.userservice.model.Userid;

public interface IUsersCustomRepo {
	public Userid getUserId(String username);
	public List<String> getUsernames();
	public String getUsernameById(Integer id);
}
