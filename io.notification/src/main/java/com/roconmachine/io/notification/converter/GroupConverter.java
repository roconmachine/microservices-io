package com.roconmachine.io.notification.converter;

import com.roconmachine.io.dataframe.notification.models.Group;
import com.roconmachine.io.notification.core.Convertable;
import com.roconmachine.io.notification.entities.GroupEntity;
import org.springframework.stereotype.Component;

@Component
public class GroupConverter implements Convertable<GroupEntity, Group> {
    @Override
    public GroupEntity toEntity(Group group) {
        GroupEntity entity = new GroupEntity();
        entity.setName(group.getName());
        return entity;
    }

    @Override
    public Group toModel(GroupEntity groupEntity) {
        Group model = new Group();
        model.setId(groupEntity.getId());
        model.setName(groupEntity.getName());
        return model;
    }
}
