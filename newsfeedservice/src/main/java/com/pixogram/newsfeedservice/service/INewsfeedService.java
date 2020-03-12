package com.pixogram.newsfeedservice.service;

import java.util.List;
import java.util.Optional;

import com.pixogram.newsfeedservice.entity.Newsfeed;
import com.pixogram.newsfeedservice.model.NewsFeedList;
import com.pixogram.newsfeedservice.model.NewsFeedModel;
import com.pixogram.newsfeedservice.model.NewsFeedResponse;



public interface INewsfeedService {
	
	public List<String> getall(Integer userId);
	public void save(NewsFeedModel action);
	/*public Optional<Newsfeed> getWithId(Integer id);
	public void updateuser(NewsfeedData action);*/
}
