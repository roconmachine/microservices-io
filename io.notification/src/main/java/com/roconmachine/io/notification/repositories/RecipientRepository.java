package com.roconmachine.io.notification.repositories;

import com.roconmachine.io.notification.entities.RecipientEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface RecipientRepository extends R2dbcRepository<RecipientEntity, Long> {
}
