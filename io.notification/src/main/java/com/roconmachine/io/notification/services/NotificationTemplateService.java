package com.roconmachine.io.notification.services;

import com.roconmachine.io.notification.core.AbstructService;
import com.roconmachine.io.notification.entities.NotificationTemplateEntity;
import com.roconmachine.io.notification.repositories.NotificationTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationTemplateService extends AbstructService<NotificationTemplateEntity, NotificationTemplateRepository> {
}
