package com.roconmachine.io.payment.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {


    public static String topicName = "payment_topic";
    @Bean
    public NewTopic paymentTopic() {
        return TopicBuilder
                .name(topicName)
                .build();
    }

}
