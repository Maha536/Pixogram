package com.pixogram.mediaservice.controller;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pixogram.mediaservice.entity.Media;
import com.pixogram.mediaservice.exception.MediaErrorResponse;
import com.pixogram.mediaservice.exception.MediaNotFoundException;
import com.pixogram.mediaservice.model.MediaData;
import com.pixogram.mediaservice.model.MediaDataModel;
import com.pixogram.mediaservice.model.MediaModel;
import com.pixogram.mediaservice.service.IMediaService;
import com.pixogram.mediaservice.service.StorageService;




@RestController
public class MediaController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IMediaService mediaService;
	
	@Autowired
	private StorageService storageService;
	
	@GetMapping("/mediabyid/{userId}")
	public ResponseEntity<MediaModel> getall(@PathVariable Integer userId){
		MediaModel medialist = new MediaModel();
		medialist.setMedialist(this.mediaService.getall(userId));
		ResponseEntity<MediaModel> result = new ResponseEntity<MediaModel>(medialist, HttpStatus.OK);
		return result;
		
	}
	
	@PostMapping(value = "/media", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public boolean save(MultipartFile file) {// ,@RequestBody MediaDataModel media) {
		/*MediaData mediamod = new MediaData();
		mediamod.setTitle(media.getTitle());
		mediamod.setDescription(media.getDescription());
		mediamod.setTags(media.getTags());
		mediamod.setUserId(media.getUserid());
		mediamod.setFile(media.getUrl());
		mediamod.setType(media.getType());
		this.mediaService.save(mediamod);*/
		this.storageService.store(file);
		return true;
	}
	
	// for saving media info in db
	@PostMapping(value = "/mediadata")
	public boolean saveData(@RequestBody MediaDataModel media) {
		MediaData mediamod = new MediaData();
		mediamod.setTitle(media.getTitle());
		mediamod.setDescription(media.getDescription());
		mediamod.setTags(media.getTags());
		mediamod.setUserId(media.getUserid());
		mediamod.setUrl(media.getUrl());
		mediamod.setType(media.getType());
		this.mediaService.save(mediamod);
		//this.storageService.store(file);
		return true;
	}
	
	@GetMapping("/mediadetails/{mediaId}")
	public ResponseEntity<MediaData> getDetailsBymediaId(@PathVariable Integer mediaId){
		MediaData data = new MediaData();
		Media record = new Media();
		Optional<Media> media = this.mediaService.getWithId(mediaId);
		if(media.isPresent())
			record = media.get();
		else {
			throw new MediaNotFoundException("Media not found");
		}
		data.setId(record.getId());
		data.setUserId(record.getUserId());
		data.setTitle(record.getTitle());
		data.setTags(record.getTags());
		data.setDescription(record.getDescription());
		data.setType(record.getMimeType());
		data.setUrl(record.getFileUrl());
		ResponseEntity<MediaData> result = new ResponseEntity<MediaData>(data, HttpStatus.OK);
		return result;
	}
	
	
	//update user
	@PutMapping("/media")
	public boolean update(@RequestBody MediaData user) {
		
		this.mediaService.updateuser(user);
		return true;
		
	}
	
	@ExceptionHandler  // ~catch
	public ResponseEntity<MediaErrorResponse> productOperationErrorHAndler(Exception ex) {
		// create error object
		MediaErrorResponse error = new MediaErrorResponse(ex.getMessage(), 
															  HttpStatus.BAD_REQUEST.value(), 
															  System.currentTimeMillis());
		ResponseEntity<MediaErrorResponse> response =
										new ResponseEntity<MediaErrorResponse>(error, HttpStatus.NOT_FOUND);
		logger.error("Exception :" + error);
		
		return response;
	}
}