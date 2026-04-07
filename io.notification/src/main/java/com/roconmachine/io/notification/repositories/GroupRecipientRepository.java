package com.roconmachine.io.notification.repositories;

import com.roconmachine.io.notification.entities.GroupRecipientEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface GroupRecipientRepository extends R2dbcRepository<GroupRecipientEntity, Long> {
}
