package com.pixogram.miscplumbing.feignproxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name ="api-gateway",url = "http://localhost:8765/")
@RibbonClient(name ="follow-service")
public interface FollowServiceProxy {
	@GetMapping("/follow-service/check-followings/mine/{mineId}/other/{otherId}")
	public ResponseEntity<Boolean> getFollowingStatus(@PathVariable Integer mineId, @PathVariable Integer otherId);

	@GetMapping("/follow-service/following/{mineId}")
	public ResponseEntity<List<Integer>> getFollowingList(@PathVariable Integer mineId);

	@GetMapping("/follow-service/followers/{mineId}")
	public ResponseEntity<List<Integer>> getFollowersList(@PathVariable Integer mineId);
}
