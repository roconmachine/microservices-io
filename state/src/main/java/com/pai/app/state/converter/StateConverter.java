package com.pai.app.state.converter;

import com.pai.app.state.core.Convertable;
import com.pai.app.state.entities.StateEntity;
import com.roconmachine.io.dataframe.state.models.State;
import org.springframework.stereotype.Component;

@Component
public class StateConverter implements Convertable<StateEntity, State> {
    @Override
    public StateEntity toEntity(State model) {
        StateEntity entity = new StateEntity();
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setRank(model.getOrder());
        entity.setReference(model.getReference());

        return entity;
    }

    @Override
    public State toModel(StateEntity entity) {
        State state = new State();
        state.setId(entity.getId());
        state.setName(entity.getName());
        state.setDescription(entity.getDescription());
        state.setDomain(entity.getDomain());
        state.setOrder(entity.getRank());
        state.setReference(entity.getReference());

        return state;
    }
}
