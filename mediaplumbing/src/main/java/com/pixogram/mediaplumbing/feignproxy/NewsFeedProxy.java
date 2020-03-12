package com.pixogram.mediaplumbing.feignproxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pixogram.mediaplumbing.model.NewsFeedModel;

@FeignClient(name ="api-gateway",url = "http://localhost:8765/")//,configuration = {MediaServiceProxy.MultipartSupportConfig.class})
@RibbonClient(name ="newsfeed-service")
public interface NewsFeedProxy {
	@PostMapping("/newsfeed-service/newsfeed")
	public boolean save(@RequestBody NewsFeedModel newsFeed);
}
