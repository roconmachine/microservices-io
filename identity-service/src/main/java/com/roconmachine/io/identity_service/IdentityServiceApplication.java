package com.roconmachine.io.identity_service;



import com.roconmachine.io.identity_service.noti.api.NotificationApi;
import com.roconmachine.io.identity_service.noti.client.ApiClient;

import com.roconmachine.io.identity_service.noti.models.MessageTemplate;
import com.roconmachine.io.identity_service.noti.models.RequestNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class IdentityServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(IdentityServiceApplication.class, args);
	}

	@Autowired
	private NotificationApi notificationApi;
	@Override
	public void run(String... args) throws Exception {

		RequestNotification requestNotification = new RequestNotification();
		requestNotification.setType(RequestNotification.TypeEnum.EMAIL);
		requestNotification.setHeader("this is the header");
		requestNotification.sender("okey@gmail.com");
		requestNotification.setDestinations(Stream.of("roconmachine@gmail.com")
				.collect(Collectors.toList()));
		requestNotification.setTemplate(new MessageTemplate("name","template of the message"));
		notificationApi.addNotification(requestNotification);
	}
}
