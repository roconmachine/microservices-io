package com.roconmachine.io.notification.repository;

import com.roconmachine.io.notification.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, Long> {
}
