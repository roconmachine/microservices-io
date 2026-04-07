package com.roconmachine.io.fgaccess.converters;

import com.roconmachine.io.dataframe.fgaccess.models.Action;
import com.roconmachine.io.fgaccess.entity.ActionEntity;

public class ActionConverter{


    public static ActionEntity toEntity(Action model) {
        return ActionEntity.builder()
                .name(model.getName())
                .action_type(model.getActionType())
                .build();
    }


    public static Action toModel(ActionEntity entity) {
        return Action.builder()
                .id(entity.action_id)
                .name(entity.name)
                .actionType(entity.action_type)
                .build();
    }
}
