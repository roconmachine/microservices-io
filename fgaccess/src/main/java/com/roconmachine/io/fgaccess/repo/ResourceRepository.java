package com.roconmachine.io.fgaccess.repo;

import com.roconmachine.io.fgaccess.entity.ResourceEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ResourceRepository extends R2dbcRepository<ResourceEntity, Long> {
}
