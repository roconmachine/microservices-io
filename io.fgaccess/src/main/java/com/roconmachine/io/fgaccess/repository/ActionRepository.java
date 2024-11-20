package com.roconmachine.io.fgaccess.repository;

import com.roconmachine.io.fgaccess.entity.ActionEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ActionRepository extends R2dbcRepository<ActionEntity, Long> {
}
