package com.roconmachine.io.fgaccess.repo;

import com.roconmachine.io.fgaccess.entity.PropertiesEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PropertyRepository extends R2dbcRepository<PropertiesEntity, Long> {
}
