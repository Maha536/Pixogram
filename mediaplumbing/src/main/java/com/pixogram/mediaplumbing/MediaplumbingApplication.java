package com.pixogram.mediaplumbing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import feign.Request;

@SpringBootApplication
@EnableFeignClients("com.pixogram.mediaplumbing.feignproxy")
@EnableEurekaClient
public class MediaplumbingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediaplumbingApplication.class, args);
	}
	
	@Bean
    Request.Options requestOptions(ConfigurableEnvironment env){
        int ribbonReadTimeout = env.getProperty("ribbon.ReadTimeout", int.class, 6000);
        int ribbonConnectionTimeout = env.getProperty("ribbon.ConnectTimeout", int.class, 3000);

        return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
    }
	
}
