package com.roconmachine.io.payment.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;
@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentProducer {

    private final KafkaTemplate<String, KafkaRequestMessage> kafkaTemplate;
    @Value("${payment.kafkaTopic}")
    private String topic;
    public void sendMessage(KafkaRequestMessage kafkaRequestMessage) {
        Message<KafkaRequestMessage> messageMessage = MessageBuilder
                .withPayload(kafkaRequestMessage)
                .setHeader(TOPIC, topic)
                .build();

        kafkaTemplate.send(messageMessage);
    }
}
