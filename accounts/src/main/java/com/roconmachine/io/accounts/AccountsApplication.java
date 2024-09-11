package com.roconmachine.io.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@ComponentScan(basePackageClasses = {GlobalExceptionHandler.class, RequestBodyInterceptor.class})
public class AccountsApplication {


	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);

	}


}
