package com.pixogram.mediaplumbing.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MediaDetailOfUser {
	private List<GalleryResponse> filelist;
}
