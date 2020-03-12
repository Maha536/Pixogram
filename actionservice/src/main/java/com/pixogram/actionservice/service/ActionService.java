package com.pixogram.actionservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pixogram.actionservice.entity.Actions;
import com.pixogram.actionservice.repository.ActionsRepository;
import com.pixogram.actionservice.repository.CustomRepo;
import com.pixogram.actionservice.model.ActionAddData;
import com.pixogram.actionservice.model.ActionCountAndStatus;
import com.pixogram.actionservice.model.ActionData;
import com.pixogram.actionservice.model.ActionsCountModel;

@Service
public class ActionService implements IActionService {
	@Autowired
	private CustomRepo custom;
	@Autowired
	private ActionsRepository actionRepository;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public List<Actions> getall(){
		List<Actions> records =this.actionRepository.findAll();
		return records;
		
	}
	
	public void save(ActionAddData action) {
		Actions data = new Actions();
		data.setMediaId(action.getMediaId());
		data.setStatus(action.getStatus());
		data.setUserId(action.getUserId());
		this.actionRepository.save(data);
		
	}
	
	public Optional<Actions> getWithId(Integer id){
		Optional<Actions> record= this.actionRepository.findById(id);
		return record;
		
	}
	
	public void updateuser(ActionData action) {
		Actions data = new Actions();
		data.setMediaId(action.getMediaId());
		data.setStatus(action.getStatus());
		data.setUserId(action.getUserId());
		data.setId(action.getId());
		this.actionRepository.save(data);
	}

	@Override
	public ActionsCountModel getLikesByMediaId(Integer id) {
		// TODO Auto-generated method stub
		ActionsCountModel count = this.custom.getLikes(id);
		logger.info("likes and unlikes count ===>>  "+count.getLikes()+"  "+count.getUnlikes());
		return count;
	}

	@Override
	public ActionCountAndStatus getLikesAndStatus(Integer mediaId, Integer userId) {
		// TODO Auto-generated method stub
		return this.custom.getCountAndStatus(mediaId, userId);
	}

}
