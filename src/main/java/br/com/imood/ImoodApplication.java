package br.com.imood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ImoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImoodApplication.class, args);
	}

}

