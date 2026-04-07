package com.roconmachine.io.notification.services;

import com.roconmachine.io.notification.core.AbstructService;
import com.roconmachine.io.notification.entities.GroupRecipientEntity;
import com.roconmachine.io.notification.repositories.GroupRecipientRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupRecipientService extends AbstructService<GroupRecipientEntity, GroupRecipientRepository> {
}
