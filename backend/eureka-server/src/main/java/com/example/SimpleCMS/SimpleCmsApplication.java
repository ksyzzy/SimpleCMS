package com.example.SimpleCMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SimpleCmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleCmsApplication.class, args);
	}

}
