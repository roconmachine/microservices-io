package com.roconmachine.io.payment;

import com.roconmachine.io.payment.producer.KafkaRequestMessage;
import com.roconmachine.io.payment.producer.PaymentProducer;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaymentApplicationTests {

	@Mock
	private PaymentProducer producer;


	@Test
	void producerTest() {
		producer.sendMessage(new KafkaRequestMessage("name", "example.com"));
		
	}

}
