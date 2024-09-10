package com.roconmachine.io.notification.controller;



import com.roconmachine.io.interfaces.NotifyApi;

import com.roconmachine.io.models.RequestNotification;
import com.roconmachine.io.notification.entity.Notification;
import com.roconmachine.io.notification.entity.NotificationStatus;
import com.roconmachine.io.notification.service.EmailService;
import com.roconmachine.io.notification.service.NotificationService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping("${api.version}${api.service}")
public class NotifyController  implements NotifyApi {

    @Autowired
    private EmailService emailService;
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ModelMapper modelMapper;
    @PostMapping("/notify")
    public ResponseEntity<Void> addNotification(RequestNotification notification) throws MessagingException {
        Notification noti  = modelMapper.map(notification, Notification.class);
        noti.setStatus(NotificationStatus.SENT);
        emailService.sendEmail(notification.getDestinations(), notification.getHeader(), notification.getTemplate().getTemplate());
        notificationService.save(noti);
        return new ResponseEntity<Void>( HttpStatus.CREATED );
    }
}
