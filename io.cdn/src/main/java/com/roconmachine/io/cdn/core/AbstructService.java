package com.roconmachine.io.cdn.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;


public class AbstructService<T, R extends R2dbcRepository<T, Long>> implements IService<T>{


    @Autowired
    protected R repository;
    @Override
    public Mono<T> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Boolean> delete(Long id) {
        return this.repository.existsById(id)
                .flatMap(aBoolean -> {
                    if (aBoolean) return this.repository.deleteById(id).then(Mono.just(true));
                    else return Mono.just(false);
                });
    }

    @Override
    public Mono<T> save(T object) {
        return this.repository.save(object);
    }

}
