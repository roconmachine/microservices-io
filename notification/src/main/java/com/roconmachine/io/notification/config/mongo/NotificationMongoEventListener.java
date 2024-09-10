package com.roconmachine.io.notification.config.mongo;

import com.roconmachine.io.notification.entity.Notification;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class NotificationMongoEventListener extends AbstractMongoEventListener<Notification> {


    private final PrimarySequenceService primarySequenceService;

    public NotificationMongoEventListener(final PrimarySequenceService primarySequenceService) {
        this.primarySequenceService = primarySequenceService;
    }

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Notification> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(primarySequenceService.getNextValue());

        }
    }
}
