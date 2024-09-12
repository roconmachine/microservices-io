package com.roconmachine.io.payment.consumer;

import com.roconmachine.io.payment.producer.KafkaRequestMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumer {

    @KafkaListener(topicPattern = "payment_topic")
    public void kafkaSubscribe(KafkaRequestMessage kafkaRequestMessage){

        log.info(kafkaRequestMessage.toString());
    }
}
