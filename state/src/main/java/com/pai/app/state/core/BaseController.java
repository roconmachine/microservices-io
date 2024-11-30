package com.pai.app.state.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController<Service extends BaseService, Model, Entity> {

    @Autowired
    private Service service;

    @Autowired
    private Convertable<Entity, Model> convertable;

    public ResponseEntity<Model> save(Model model){
        Entity e = convertable.toEntity(model);
        Entity entity = (Entity) service.save(e);
        if (entity != null)
        {
            //return ResponseEntity.status(HttpStatus.CREATED).build();
            return ResponseEntity.status(HttpStatus.CREATED).body(convertable.toModel(entity));
        }
        else return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }



}
