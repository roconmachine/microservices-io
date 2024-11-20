package com.roconmachine.io.fgaccess.repository;

import com.roconmachine.io.fgaccess.entity.SubjectEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface SubjectRepository extends R2dbcRepository<SubjectEntity, Long> {
}
