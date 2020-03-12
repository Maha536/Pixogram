package com.pixogram.newsfeedservice.model;

import java.util.List;

import com.pixogram.newsfeedservice.entity.Newsfeed;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewsFeedList {
	
	public List<NewsFeedResponse> newsfeed;
}
