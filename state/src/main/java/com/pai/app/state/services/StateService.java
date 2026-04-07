package com.pai.app.state.services;

import com.pai.app.state.core.BaseService;
import com.pai.app.state.entities.StateEntity;
import com.pai.app.state.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class StateService extends BaseService<StateEntity, StateRepository> {

    @Autowired
    private StateRepository stateRepository;
    public StateEntity findRoot(String pai) {
        List<StateEntity> list =stateRepository.findAllByIsRootAndAppName(true, pai);
        if (list != null && !list.isEmpty())
        {
            return list.get(0);
        }

        return null;
    }
}
