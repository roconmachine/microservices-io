package com.roconmachine.io.notification.repositories;

import com.roconmachine.io.notification.entities.EmailNotificationEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface EmailNotificationRepository extends R2dbcRepository<EmailNotificationEntity, Long> {

    Flux<EmailNotificationEntity> findAllByStatusAndSeverity(String status, String severity);
    Flux<EmailNotificationEntity> findAllByStatusOrSeverity(String status, String severity);
}
