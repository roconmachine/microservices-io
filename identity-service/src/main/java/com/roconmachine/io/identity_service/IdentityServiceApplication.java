package com.roconmachine.io.identity_service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IdentityServiceApplication
//		implements CommandLineRunner
{

	public static void main(String[] args) {
		SpringApplication.run(IdentityServiceApplication.class, args);
	}

//	@Autowired
//	private NotificationApi notificationApi;
//	@Override
//	public void run(String... args) throws Exception {
//
//		RequestNotification requestNotification = new RequestNotification();
//		requestNotification.setType(RequestNotification.TypeEnum.EMAIL);
//		requestNotification.setHeader("this is the header");
//		requestNotification.sender("okey@gmail.com");
//		requestNotification.setDestinations(Stream.of("roconmachine@gmail.com")
//				.collect(Collectors.toList()));
//		requestNotification.setTemplate(new MessageTemplate("name","template of the message"));
//		notificationApi.addNotification(requestNotification);
//	}
}
