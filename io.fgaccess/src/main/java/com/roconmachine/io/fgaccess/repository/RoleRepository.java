package com.roconmachine.io.fgaccess.repository;

import com.roconmachine.io.fgaccess.entity.RoleEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface RoleRepository extends R2dbcRepository<RoleEntity, Long> {

}
