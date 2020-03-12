package com.pixogram.actionservice.repository;

import com.pixogram.actionservice.model.ActionCountAndStatus;
import com.pixogram.actionservice.model.ActionsCountModel;

public interface CustomRepo {
	
	public ActionsCountModel getLikes(Integer mediaId);
	public ActionCountAndStatus getCountAndStatus(Integer mediaId,Integer userId);
}
