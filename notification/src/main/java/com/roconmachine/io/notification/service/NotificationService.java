package com.roconmachine.io.notification.service;

import com.roconmachine.io.notification.entity.Notification;
import com.roconmachine.io.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;

    public Notification save(Notification notification){
        return repository.save(notification);
    }
}
