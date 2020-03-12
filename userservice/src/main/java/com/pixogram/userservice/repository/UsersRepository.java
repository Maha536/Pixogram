package com.pixogram.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pixogram.userservice.entity.Users;
import com.pixogram.userservice.model.Userid;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
	List<Users> findByUserName(String username);
	List<Users> findByUserNameContaining(String username);
}
