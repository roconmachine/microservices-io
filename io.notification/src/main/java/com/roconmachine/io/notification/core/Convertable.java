package com.roconmachine.io.notification.core;

import org.springframework.stereotype.Component;


public interface Convertable<Entity, Model>{
    public  Entity toEntity(Model model);

    public Model toModel(Entity entity);
}
