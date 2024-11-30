package com.roconmachine.io.notification.repositories;

import com.roconmachine.io.notification.entities.NotificationTemplateEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface NotificationTemplateRepository extends R2dbcRepository<NotificationTemplateEntity, Long> {
}
