package com.roconmachine.io.fgaccess.repo;

import com.roconmachine.io.fgaccess.entity.PolicyMappingEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PolicyMappingRepository extends R2dbcRepository<PolicyMappingEntity, Long> {
}
