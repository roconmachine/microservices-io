package com.roconmachine.io.fgaccess.converters;

import com.roconmachine.io.dataframe.fgaccess.models.Role;
import com.roconmachine.io.fgaccess.entity.RoleEntity;

public class RoleConverter {


    public static Role toModel(RoleEntity roleEntity){
        return Role.builder()
                .id(roleEntity.role_id)
                .name(roleEntity.getName())
                .build();
    }

    public static RoleEntity toEntity(Role roleEntity){
        return RoleEntity.builder()
//                .id(roleEntity.role_id)
                .name(roleEntity.getName())
                .build();
    }
}
