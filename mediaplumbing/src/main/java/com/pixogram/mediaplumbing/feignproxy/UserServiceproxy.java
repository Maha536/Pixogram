package com.pixogram.mediaplumbing.feignproxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pixogram.mediaplumbing.model.ProfileData;


@FeignClient(name ="api-gateway",url = "http://localhost:8765/")//,configuration = {MediaServiceProxy.MultipartSupportConfig.class})
@RibbonClient(name ="user-service")
public interface UserServiceproxy {
	@GetMapping("/user-service/profile/{userId}")
	public ResponseEntity<ProfileData> getProfileById(@PathVariable Integer userId);
	
	@GetMapping("/user-service/comments/{userId}")
	public ResponseEntity<String> getCommentedUsername(@PathVariable Integer userId);
}
