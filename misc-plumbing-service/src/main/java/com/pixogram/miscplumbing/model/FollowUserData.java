package com.pixogram.miscplumbing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowUserData {
	private Integer userId;
	private String username;
	private String name;
	private String url;
}
