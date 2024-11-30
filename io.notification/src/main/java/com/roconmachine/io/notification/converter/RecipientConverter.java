package com.roconmachine.io.notification.converter;

import com.roconmachine.io.dataframe.notification.models.Recipient;
import com.roconmachine.io.notification.core.Convertable;
import com.roconmachine.io.notification.entities.RecipientEntity;

public class RecipientConverter implements Convertable<RecipientEntity, Recipient> {
    @Override
    public RecipientEntity toEntity(Recipient recipient) {
        RecipientEntity entity = new RecipientEntity();
        entity.setName(recipient.getName());
        entity.setEmail(recipient.getEmail());
        entity.setPhone(recipient.getPhone());
        entity.setDevice_id(recipient.getDeviceId());
        return entity;
    }

    @Override
    public Recipient toModel(RecipientEntity recipientEntity) {
        Recipient model = new Recipient();
        model.setName(recipientEntity.getName());
        model.setPhone(recipientEntity.getPhone());
        model.setEmail(recipientEntity.getEmail());
        model.setDeviceId(recipientEntity.getDevice_id());
        return model;
    }
}
