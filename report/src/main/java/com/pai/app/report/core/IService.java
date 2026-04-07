package com.pai.app.report.core;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    Optional<T> getById(Long id);
    void delete(Long id);
    Optional<T> save(T object);

    Optional<List<T>> saveAll(List<T> objects);
    Optional<T> update(T object);
}
