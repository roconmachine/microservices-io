package com.roconmachine.io.fgaccess.repository;

import com.roconmachine.io.fgaccess.entity.PolicyEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PolicyRepository extends R2dbcRepository<PolicyEntity, Long> {
}
