package com.roconmachine.io.fgaccess.service;


import com.roconmachine.io.fgaccess.core.AbstructService;
import com.roconmachine.io.fgaccess.entity.UserRoleEntity;
import com.roconmachine.io.fgaccess.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleService extends AbstructService<UserRoleEntity, UserRoleRepository> {

}
