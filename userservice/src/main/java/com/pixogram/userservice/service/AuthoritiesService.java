package com.pixogram.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixogram.userservice.entity.Authorities;
import com.pixogram.userservice.repository.AuthorityRepository;

@Service
public class AuthoritiesService {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	public void saveauthority(Authorities role) {
		this.authorityRepository.save(role);
	}

}
