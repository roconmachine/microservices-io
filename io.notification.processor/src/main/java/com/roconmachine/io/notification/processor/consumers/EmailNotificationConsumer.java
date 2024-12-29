package com.roconmachine.io.notification.processor.consumers;

import com.roconmachine.io.dataframe.notification.client.api.NotificationApi;
import com.roconmachine.io.notification.processor.schema.MessageRequest;
import com.roconmachine.io.notification.processor.services.EmailService;
import com.roconmachine.io.notification.processor.services.IEmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailNotificationConsumer {

//    @Autowired
    private final   IEmailService emailService;
    private final NotificationApi notificationApi;

    @Value("${service.completed-status-name}")
    private String statusName;

    public EmailNotificationConsumer(IEmailService emailService, NotificationApi notificationApi) {
        this.emailService = emailService;
        this.notificationApi = notificationApi;
    }

    @KafkaListener(topics = "${service.notification-kafka-topic}" , containerFactory = "kafkaListenerContainerFactory")
    public void kafkaSubscribe(MessageRequest kafkaRequestMessage
            , Acknowledgment acknowledgment
    ){
        System.out.println(kafkaRequestMessage);
        try {
            emailService.sendMail(kafkaRequestMessage);
            notificationApi.setStatus(kafkaRequestMessage.getId(), statusName).log()
                    .doOnError(throwable -> {
                        log.error(throwable.getMessage());
                    })
                    .doOnSuccess(unused ->
                        log.info("status update done"))
                    .subscribe();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        acknowledgment.acknowledge();
    }
}
