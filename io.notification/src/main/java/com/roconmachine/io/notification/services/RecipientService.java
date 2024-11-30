package com.roconmachine.io.notification.services;

import com.roconmachine.io.notification.core.AbstructService;
import com.roconmachine.io.notification.entities.RecipientEntity;
import com.roconmachine.io.notification.repositories.RecipientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipientService extends AbstructService<RecipientEntity, RecipientRepository> {
}
