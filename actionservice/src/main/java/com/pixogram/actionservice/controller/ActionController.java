package com.pixogram.actionservice.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pixogram.actionservice.model.ActionModel;
import com.pixogram.actionservice.model.ActionsCountModel;
import com.pixogram.actionservice.service.IActionService;
import com.pixogram.actionservice.entity.Actions;
import com.pixogram.actionservice.exception.ActionErrorResponse;
import com.pixogram.actionservice.exception.ActionNotFoundException;
import com.pixogram.actionservice.model.ActionAddData;
import com.pixogram.actionservice.model.ActionCountAndStatus;
import com.pixogram.actionservice.model.ActionData;

@CrossOrigin("*")
@RestController
public class ActionController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IActionService actionService;
	
	@GetMapping("/action")
	public ResponseEntity<ActionModel> getallactions(){
		ActionModel data = new ActionModel();
		data.setActionlist(this.actionService.getall());
		ResponseEntity<ActionModel> result = new ResponseEntity<ActionModel>(data, HttpStatus.OK);
		return result;
		
	}
	// to get count of likes and unlikes
	@GetMapping("/getlikes/{id}")
	public ResponseEntity<ActionsCountModel> getLikes(@PathVariable Integer id) {
		logger.info("media id ====>>>  "+id);
		ActionsCountModel count = this.actionService.getLikesByMediaId(id);
		ResponseEntity<ActionsCountModel> result = new ResponseEntity<ActionsCountModel>(count,HttpStatus.OK);
		return result;
		
	}
	
	//get actions count and status of media
	@GetMapping("/getlikes/{mediaId}/status/{userId}")
	public ResponseEntity<ActionCountAndStatus> getLikesAndStatus(@PathVariable Integer mediaId,@PathVariable Integer userId) {
		logger.info("media id ====>>>  "+mediaId);
		ActionCountAndStatus count = this.actionService.getLikesAndStatus(mediaId, userId);
		ResponseEntity<ActionCountAndStatus> result = 
				new ResponseEntity<ActionCountAndStatus>(count,HttpStatus.OK);
		return result;
		
	}
	
	@PostMapping("/action")
	public boolean save(@RequestBody ActionAddData action) {
		this.actionService.save(action);
		return true;
	}
	
	@GetMapping("/action/{actionId}")
	public ResponseEntity<ActionData> getById(@PathVariable Integer actionId){
		ActionData data = new ActionData();
		Actions record = new Actions();
		Optional<Actions> action = this.actionService.getWithId(actionId);
		if(action.isPresent())
			record = action.get();
		else {
			throw new ActionNotFoundException("action not found");
		}
		data.setId(record.getId());
		data.setUserId(record.getUserId());
		data.setMediaId(record.getMediaId());
		data.setStatus(record.getStatus());
		ResponseEntity<ActionData> result = new ResponseEntity<ActionData>(data, HttpStatus.OK);
		return result;
	}
	
	
	//update user
	@PutMapping("/action")
	public boolean update(@RequestBody ActionData user) {
		this.actionService.updateuser(user);
		return true;
	}
	
	@ExceptionHandler  // ~catch
	public ResponseEntity<ActionErrorResponse> productOperationErrorHAndler(Exception ex) {
		// create error object
		ActionErrorResponse error = new ActionErrorResponse(ex.getMessage(), 
															  HttpStatus.BAD_REQUEST.value(), 
															  System.currentTimeMillis());
		ResponseEntity<ActionErrorResponse> response =
										new ResponseEntity<ActionErrorResponse>(error, HttpStatus.NOT_FOUND);
		logger.error("Exception :" + error);
		
		return response;
	}
	
}
