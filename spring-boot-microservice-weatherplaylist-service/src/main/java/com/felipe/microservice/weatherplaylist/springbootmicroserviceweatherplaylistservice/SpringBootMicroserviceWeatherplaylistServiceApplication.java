package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice")
@EnableDiscoveryClient
@EnableCircuitBreaker
public class SpringBootMicroserviceWeatherplaylistServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMicroserviceWeatherplaylistServiceApplication.class, args);
    }

}
