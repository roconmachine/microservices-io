package com.roconmachine.io.notification.scheduler;

import com.roconmachine.io.notification.entities.EmailNotificationEntity;
import com.roconmachine.io.notification.entities.NotificationTemplateEntity;
import com.roconmachine.io.notification.kafka.MessageRequest;
import com.roconmachine.io.notification.kafka.NotificationType;
import com.roconmachine.io.notification.services.EmailNotificationService;
import com.roconmachine.io.notification.services.NotificationTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Calendar;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationPushScheduler {
    private final EmailNotificationService emailNotificationService;
    private final KafkaTemplate<String, MessageRequest> kafkaTemplate;
    private final NotificationTemplateService notificationTemplateService;

    @Value("${service.notification-kafka-topic}")
    private String topic;
    @Scheduled(fixedRate = 20000)
    void schedule(){
        emailNotificationService.search("PENDING", null)
                .flatMap(emailNotificationEntity ->
                        notificationTemplateService.getByName(emailNotificationEntity.getTemplate_name())
                                .doOnNext(notificationTemplateEntity ->
                                        sendMessage(emailNotificationEntity, notificationTemplateEntity)
                                ) // Side-effect of sending the message
                                .switchIfEmpty(Mono.defer(() -> {
                                    // Handle the case when no template is found
                                    System.out.println("No template found for " + emailNotificationEntity.getTemplate_name());
                                    return Mono.empty();
                                }))
                                .then(Mono.just(true)) // Return a Mono<Boolean> indicating success
                )
                .log() // Optional, for logging
                .subscribe();

//        emailNotificationService.search("PENDING",null)
//                .map(emailNotificationEntity -> {
//                    return notificationTemplateService.getByName(emailNotificationEntity.getTemplate_name())
//                            .map(notificationTemplateEntity -> {
//                                        sendMessage(emailNotificationEntity, notificationTemplateEntity);
//                                        return true;
//                                    }
//
//                            );
//
//                })
//                .log().subscribe();

        System.out.println(Calendar.getInstance().getTime().toString());
    }


    public void sendMessage(EmailNotificationEntity emailNotificationEntity, NotificationTemplateEntity notificationTemplateEntity) {

        MessageRequest message = getMessageObject(emailNotificationEntity, notificationTemplateEntity);

        Message<MessageRequest> messageMessage = MessageBuilder
                .withPayload(message)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .setHeader(KafkaHeaders.KEY,"mail-pri")
                .build();

        try {

            CompletableFuture<SendResult<String, MessageRequest>> future = kafkaTemplate.send(messageMessage);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    log.info("Produced data :: " + result.toString());
                }
                else {
                    log.error(ex.getMessage());
                }
            });

        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }

    private MessageRequest getMessageObject(EmailNotificationEntity emailNotificationEntity, NotificationTemplateEntity template) {
        MessageRequest request = new MessageRequest();
        request.setId(emailNotificationEntity.getId());
        request.setType(NotificationType.EMAIL.toString());
        request.setBodyTemplate(template.getBody_template());
        request.setSubjectTemplate(template.getSubject_template());
        request.setBodyPlaceholder(emailNotificationEntity.getBody_placeholder());
        request.setSubjectPlaceholder(emailNotificationEntity.getSubject_placeholder());
        request.setSeverity(emailNotificationEntity.getSeverity());
        request.setStatus(emailNotificationEntity.getStatus());
        request.setRecipients(emailNotificationEntity.getRecipients());
        return request;
    }
}
