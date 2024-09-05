package com.roconmachine.io.identity_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class IdentityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdentityServiceApplication.class, args);
	}

}
