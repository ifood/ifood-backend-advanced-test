package com.ifood.backend.advancedtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableCaching
@EnableFeignClients
public class AdvancedTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedTestApplication.class, args);
	}

}
