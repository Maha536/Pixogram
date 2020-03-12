package com.pixogram.mediaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pixogram.mediaservice.entity.Media;

public interface MediaRepository extends JpaRepository<Media, Integer> {
	
	public List<Media> findByUserId(Integer userId);

}
