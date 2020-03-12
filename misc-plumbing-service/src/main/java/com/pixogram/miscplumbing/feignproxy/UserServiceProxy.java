package com.pixogram.miscplumbing.feignproxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.pixogram.miscplumbing.model.SearchedUserModelList;
import com.pixogram.miscplumbing.model.UserOutput;




@FeignClient(name ="api-gateway",url = "http://localhost:8765/")
@RibbonClient(name ="user-service")
public interface UserServiceProxy {
	@GetMapping("/user-service/search-users/{searchText}")
	public ResponseEntity<SearchedUserModelList> getSearchedUsers(@PathVariable String searchText);
	
	@GetMapping("/user-service/users/{userId}")
	public ResponseEntity<UserOutput> getById(@PathVariable Integer userId);
}







