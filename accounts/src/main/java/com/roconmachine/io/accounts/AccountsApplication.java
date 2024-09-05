package com.roconmachine.io.accounts;

import com.roconmachine.io.accounts.config.PrimarySequence;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class AccountsApplication {


	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);

	}


}
