package com.pixogram.actionservice.service;

import java.util.List;
import java.util.Optional;

import com.pixogram.actionservice.entity.Actions;
import com.pixogram.actionservice.model.ActionAddData;
import com.pixogram.actionservice.model.ActionCountAndStatus;
import com.pixogram.actionservice.model.ActionData;
import com.pixogram.actionservice.model.ActionsCountModel;

public interface IActionService {
	
	public List<Actions> getall();
	public void save(ActionAddData action);
	public Optional<Actions> getWithId(Integer id);
	public void updateuser(ActionData action);
	public ActionsCountModel getLikesByMediaId(Integer id);
	public ActionCountAndStatus getLikesAndStatus(Integer mediaId,Integer userId);
	
	
}
