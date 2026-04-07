package com.pai.app.state.converter;

import com.pai.app.state.core.Convertable;
import com.pai.app.state.entities.LifecycleEntity;
import com.roconmachine.io.dataframe.state.models.StateMapping;
import org.springframework.stereotype.Component;

@Component
public class LifecycleConverter implements Convertable<LifecycleEntity, StateMapping> {
    @Override
    public LifecycleEntity toEntity(StateMapping model) {
        LifecycleEntity entity = new LifecycleEntity();
        entity.setActionId(model.getAction());
        entity.setFromState(model.getFromState());
        entity.setToState(model.getToState());
        return entity;
    }

    @Override
    public StateMapping toModel(LifecycleEntity entity) {
        StateMapping stateMapping = new StateMapping();
        stateMapping.setAction(entity.getActionId());
        stateMapping.setFromState(entity.getFromState());
        stateMapping.setToState(entity.getToState());
        return stateMapping;
    }
}
