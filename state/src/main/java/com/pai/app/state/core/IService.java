package com.pai.app.state.core;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    Optional<T> getById(Long id);
    void delete(Long id);
    T save(T entity);

    List<T> saveAll(List<T> entities);
    Optional<T> update(T object);
}
