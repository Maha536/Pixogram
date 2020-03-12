package com.pixogram.mediaservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixogram.mediaservice.entity.Media;
import com.pixogram.mediaservice.model.MediaData;
import com.pixogram.mediaservice.model.MediaModel;
import com.pixogram.mediaservice.repository.MediaRepository;


@Service
public class MediaService implements IMediaService {
	
	@Autowired
	private MediaRepository mediaRepository;
	
	public List<MediaData> getall(Integer userId) {
		List<MediaData> records = new ArrayList<MediaData>();
		List<Media> mediafiles = this.mediaRepository.findByUserId(userId);
		records = mediafiles.stream()
				.map(media -> {
					MediaData mediaModel = new MediaData(media.getId(),media.getUserId(),media.getTitle(),
							media.getDescription(),media.getTags(),media.getMimeType(),media.getFileUrl());
					return mediaModel;
				})
				.collect(Collectors.toList());
		return records;	
	}
	
	public void save(MediaData media) {
		Media data = new Media();
		data.setUserId(media.getUserId());
		data.setTitle(media.getTitle());
		data.setDescription(media.getDescription());
		data.setFileUrl(media.getUrl());
		data.setTags(media.getTags());
		data.setMimeType(media.getType());
		
		this.mediaRepository.save(data);
		
	}
	
	public Optional<Media> getWithId(Integer id){
		Optional<Media> record= this.mediaRepository.findById(id);
		return record;
		
	}
	
	public void updateuser(MediaData media) {
		Media data = new Media();
		data.setUserId(media.getUserId());
		data.setId(media.getId());
		data.setTitle(media.getTitle());
		data.setDescription(media.getDescription());
		data.setTags(media.getTags());
		data.setMimeType(media.getType());
		data.setFileUrl(media.getUrl());
		this.mediaRepository.save(data);
	}
}
