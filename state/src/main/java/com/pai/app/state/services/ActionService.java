package com.pai.app.state.services;

import com.pai.app.state.core.BaseService;
import com.pai.app.state.entities.ActionEntity;
import com.pai.app.state.repositories.ActionRepository;
import org.springframework.stereotype.Service;

@Service
public class ActionService extends BaseService<ActionEntity, ActionRepository> {
}
