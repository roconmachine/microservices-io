package com.roconmachine.io.fgaccess.converters;

import com.roconmachine.io.dataframe.fgaccess.models.UserRole;
import com.roconmachine.io.fgaccess.entity.UserRoleEntity;

public class UserRoleConverter {

    public static UserRole toModel(UserRoleEntity entity){
        return UserRole.builder()
                .id(entity.user_role_id)
                .userId(entity.user_id)
                .roleId(entity.role_id)
                .build();
    }


    public static UserRoleEntity toEntity(UserRole model){
        return UserRoleEntity.builder()
                .user_id(model.getUserId())
                .role_id(model.getRoleId())
                .build();
    }
}
