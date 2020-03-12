package com.pixogram.newsfeedservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixogram.newsfeedservice.entity.Newsfeed;
import com.pixogram.newsfeedservice.model.NewsFeedList;
import com.pixogram.newsfeedservice.model.NewsFeedModel;
import com.pixogram.newsfeedservice.model.NewsFeedResponse;
import com.pixogram.newsfeedservice.repository.NewsfeedRepository;

@Service
public class NewsfeedService implements INewsfeedService{
	
	@Autowired
	private NewsfeedRepository newsfeedRepository;
	
	public List<String> getall(Integer userId){
		
		List<Newsfeed> records = this.newsfeedRepository.findByUserId(userId);
		List<String> feed = new ArrayList<String>();
		for(Newsfeed news : records ) {
			feed.add(news.getFeed()+" On "+news.getCreatedOn());
		}
		
		return feed;
		
	}
	
	public void save(NewsFeedModel newsfeed) {
		Newsfeed data = new Newsfeed();
		data.setFeed(newsfeed.getFeed());
		data.setUserId(newsfeed.getUserId());
		this.newsfeedRepository.save(data);
		
	}
	/*
	public Optional<Newsfeed> getWithId(Integer id){
		Optional<Newsfeed> record= this.newsfeedRepository.findById(id);
		return record;
		
	}
	
	public void updateuser(NewsfeedData feed) {
		Newsfeed data = new Newsfeed();
		data.setCreatedOn(feed.getCreatedOn());
		data.setFeed(feed.getFeed());
		data.setId(feed.getId());
		data.setUserId(feed.getUserId());
		
		this.newsfeedRepository.save(data);
	}*/

}
