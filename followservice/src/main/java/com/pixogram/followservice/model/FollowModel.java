package com.pixogram.followservice.model;

import java.util.List;

import com.pixogram.followservice.entity.Follow;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FollowModel {
	
	public List<Follow> followlist;
}
