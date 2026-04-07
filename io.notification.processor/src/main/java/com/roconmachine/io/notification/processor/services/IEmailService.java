package com.roconmachine.io.notification.processor.services;

import com.roconmachine.io.notification.processor.schema.MessageRequest;

public interface IEmailService {
    void sendMail(MessageRequest kafkaRequestMessage);
}
