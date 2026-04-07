package com.roconmachine.io.notification.converter;

import com.roconmachine.io.dataframe.notification.models.Notification;
import com.roconmachine.io.notification.core.Convertable;
import com.roconmachine.io.notification.entities.EmailNotificationEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class EmailNotificaitonConverter implements Convertable<EmailNotificationEntity, Notification> {

    @Override
    public EmailNotificationEntity toEntity(Notification notification) {
        EmailNotificationEntity entity  = new EmailNotificationEntity();
        entity.setTemplate_name(notification.getTemplateName());
        if (notification.getBodyPlaceholders() != null & !notification.getBodyPlaceholders().isEmpty())
        {
            entity.setBody_placeholder(String.join(";", notification.getBodyPlaceholders()));
        }
        if (notification.getSubjectPlaceholders() != null & !notification.getSubjectPlaceholders().isEmpty())
        {
            entity.setSubject_placeholder(String.join(";", notification.getSubjectPlaceholders()));
        }

        if (notification.getRecipients() != null & !notification.getRecipients().isEmpty())
        {
            entity.setRecipients(String.join(";", notification.getRecipients()));
        }
        entity.setSeverity(notification.getSeverity().getValue());
        return entity;
    }

    @Override
    public Notification toModel(EmailNotificationEntity emailNotificationEntity) {
        Notification model = new Notification();
        model.setId(emailNotificationEntity.getId());
        model.setTemplateName(emailNotificationEntity.getTemplate_name());
        if (emailNotificationEntity.getSubject_placeholder() != null)
            model.setSubjectPlaceholders(Arrays.stream(emailNotificationEntity.getSubject_placeholder().split(";")).toList());

        if (emailNotificationEntity.getBody_placeholder() != null)
            model.setBodyPlaceholders(Arrays.stream(emailNotificationEntity.getBody_placeholder().split(";")).toList());
        if (emailNotificationEntity.getRecipients() != null)
            model.setRecipients(Arrays.stream(emailNotificationEntity.getRecipients().split(";")).toList());

        model.setSeverity(Notification.SeverityEnum.fromValue(emailNotificationEntity.getSeverity()));
        return model;
    }
}
