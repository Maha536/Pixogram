package com.pixogram.mediaplumbing.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompleteProfileData {
	public Integer userid;
	public String username;
	public String name;
	public String profile;
	public List<ProfileMediaResponce> mediaList;
}
