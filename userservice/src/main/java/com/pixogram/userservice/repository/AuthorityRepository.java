package com.pixogram.userservice.repository;


import com.pixogram.userservice.entity.Authorities;

public interface AuthorityRepository{
	
	public void save(Authorities auth);
	public void delete(Authorities auth);
}
