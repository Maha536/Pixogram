package com.pixogram.mediaplumbing.feignproxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.pixogram.mediaplumbing.model.ActionCountAndStatus;
import com.pixogram.mediaplumbing.model.ActionsCountModel;

@FeignClient(name ="api-gateway",url = "http://localhost:8765/")
@RibbonClient(name ="action-service")
public interface ActionServiceProxy {
	
	@GetMapping("/action-service/getlikes/{id}")
	public ResponseEntity<ActionsCountModel> getLikes(@PathVariable Integer id);
	
	@GetMapping("/action-service/getlikes/{mediaId}/status/{userId}")
	public ResponseEntity<ActionCountAndStatus> getLikesAndStatus(@PathVariable Integer mediaId,@PathVariable Integer userId);
	
}
