package com.roconmachine.io.payment.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.springframework.kafka.support.KafkaHeaders.KEY;
import static org.springframework.kafka.support.KafkaHeaders.TOPIC;
@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentProducer {

    private final KafkaTemplate<String, KafkaRequestMessage> kafkaTemplate;
    @Value("${payment.kafkaTopic}")
    private String topic;
    public void sendMessage(KafkaRequestMessage kafkaRequestMessage) throws ExecutionException, InterruptedException {
        Message<KafkaRequestMessage> messageMessage = MessageBuilder
                .withPayload(kafkaRequestMessage)
                .setHeader(TOPIC, topic)
                .setHeader(KEY, "email")
                .build();

        CompletableFuture<SendResult<String, KafkaRequestMessage>> result = kafkaTemplate.send(messageMessage);
        result.whenComplete((result1, ex) -> {
            if (ex == null) {
                System.out.println("send successfully");
            }
            else {
                System.out.println(ex.getMessage());
            }
        });
    }
}
