package com.roconmachine.io.fgaccess.repository;

import com.roconmachine.io.fgaccess.entity.UserRoleEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface UserRoleRepository extends R2dbcRepository<UserRoleEntity, Long> {
}
