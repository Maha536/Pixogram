package com.pixogram.newsfeedservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pixogram.newsfeedservice.entity.Newsfeed;

public interface NewsfeedRepository extends JpaRepository<Newsfeed, Integer> {
  List<Newsfeed> findByUserId(Integer userId);
}
