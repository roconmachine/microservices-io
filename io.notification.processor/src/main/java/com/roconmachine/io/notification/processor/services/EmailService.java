package com.roconmachine.io.notification.processor.services;

import com.roconmachine.io.notification.processor.schema.MessageRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@Slf4j
//@RequiredArgsConstructor
public class EmailService implements IEmailService{

    private final JavaMailSender mailSender;
    @Value("${service.email-from}")
    private String emailFrom;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMail(MessageRequest message) {
        String emailBody = message.getBodyTemplate();
        String[] emailBodyParams = message.getBodyPlaceholder().split(";");
        for (int i =0; i < emailBodyParams.length; i++){
            emailBody = emailBody.replace("#" + i , emailBodyParams[i]);
        }

        String emailSuubject = message.getSubjectTemplate();
        String[] emailSubjectParams = message.getSubjectPlaceholder().split(";");
        for (int i =0; i < emailSubjectParams.length; i++){
            emailSuubject = emailSuubject.replace("#" + i , emailSubjectParams[i]);
        }


        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
            messageHelper.setFrom(emailFrom);
            messageHelper.setTo(message.getRecipients().split(";"));
            messageHelper.setText(emailBody, true);
            messageHelper.setSubject(emailSuubject);
            mailSender.send(mimeMessage);
            log.info("mail send successfully.");
        }catch (MessagingException messagingException){
            throw new RuntimeException(messagingException.getMessage());
        }


    }
}
