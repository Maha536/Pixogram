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
public class MediaDetails {
	private Integer id;
	private Integer userId;
	private String title;
	private String description;
	private String tags;
	private String type;
	private String url;
	private Integer likes;
	private Integer unlikes;
	private List<CommentsResponse> commentsList;
}
