package com.roconmachine.io.fgaccess.core;

import reactor.core.publisher.Mono;

public interface IService<T> {
    Mono<T> getById(Long id);
    Mono<Boolean> delete(Long id);
    Mono<T> save(T object);
}
