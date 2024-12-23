package com.roconmachine.io.notification.processor.consumers;

import com.roconmachine.io.notification.processor.schema.MessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailNotificationConsumer {
    //private final EmailService emailService;

    @KafkaListener(topics = "${service.notification-kafka-topic}" , containerFactory = "kafkaListenerContainerFactory")
    public void kafkaSubscribe(MessageRequest kafkaRequestMessage
            , Acknowledgment acknowledgment
    ){


        System.out.println(kafkaRequestMessage);
        acknowledgment.acknowledge();
    }
}
