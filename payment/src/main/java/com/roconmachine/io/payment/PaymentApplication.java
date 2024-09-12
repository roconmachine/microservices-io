package com.roconmachine.io.payment;

import com.roconmachine.io.payment.producer.KafkaRequestMessage;
import com.roconmachine.io.payment.producer.PaymentProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class PaymentApplication implements CommandLineRunner {

	@Autowired
	private PaymentProducer producer;
	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		producer.sendMessage(new KafkaRequestMessage("name", "example.com"));
	}
}
