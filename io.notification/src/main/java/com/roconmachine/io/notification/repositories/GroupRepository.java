package com.roconmachine.io.notification.repositories;

import com.roconmachine.io.notification.entities.GroupEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface GroupRepository extends R2dbcRepository<GroupEntity, Long> {
    Mono<Void> deleteByName(String name);
    Mono<GroupEntity> getByName(String name);
}
