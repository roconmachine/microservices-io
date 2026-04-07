package com.roconmachine.io.notification.services;

import com.roconmachine.io.notification.core.AbstructService;
import com.roconmachine.io.notification.entities.NotificationTemplateEntity;
import com.roconmachine.io.notification.repositories.NotificationTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NotificationTemplateService extends AbstructService<NotificationTemplateEntity, NotificationTemplateRepository> {
    private final NotificationTemplateRepository notificationTemplateRepository;
    public Mono<NotificationTemplateEntity> getByName(String templateName){
        return notificationTemplateRepository.getByName(templateName);
    }
}
