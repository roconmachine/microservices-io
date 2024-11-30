package com.pai.app.state.controller;

import com.pai.app.state.core.BaseController;
import com.pai.app.state.entities.LifecycleEntity;
import com.pai.app.state.repositories.LifecycleRepository;
import com.pai.app.state.services.LifecycleService;
import com.roconmachine.io.dataframe.state.models.StateMapping;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${service.name}/${service.apiversion}")
@RequiredArgsConstructor
public class TestController extends BaseController<LifecycleService, StateMapping, LifecycleEntity> {


    @RequestMapping(
            method = {RequestMethod.POST},
            value = {"/test"},
            consumes = {"application/json"}
    )
    public ResponseEntity<StateMapping> save(@Valid @RequestBody StateMapping stateMapping)
    {
        return super.save(stateMapping);
    }



    private LifecycleRepository repository;

}
