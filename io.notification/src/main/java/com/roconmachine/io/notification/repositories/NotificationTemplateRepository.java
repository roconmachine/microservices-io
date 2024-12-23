package com.roconmachine.io.notification.repositories;

import com.roconmachine.io.notification.entities.NotificationTemplateEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface NotificationTemplateRepository extends R2dbcRepository<NotificationTemplateEntity, Long> {
    Mono<NotificationTemplateEntity> getByName(String name);
}
