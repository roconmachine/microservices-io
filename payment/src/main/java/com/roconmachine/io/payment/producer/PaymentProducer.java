package com.roconmachine.io.payment.producer;

import com.roconmachine.io.payment.config.KafkaConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public void sendMessage(KafkaRequestMessage kafkaRequestMessage) {
        Message<KafkaRequestMessage> messageMessage = MessageBuilder
                .withPayload(kafkaRequestMessage)
                .setHeader(TOPIC, KafkaConfig.topicName)
                .build();

        kafkaTemplate.send(messageMessage);
    }
}
