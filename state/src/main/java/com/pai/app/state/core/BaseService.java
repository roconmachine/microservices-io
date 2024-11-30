package com.pai.app.state.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService <T, R extends JpaRepository<T, Long>>  implements IService<T> {

    @Autowired
    private R repository;
    @Override
    public T save(T object) {
        return repository.save(object);
    }


    public T save1(T object) {
        return repository.save(object);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<T> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<T> saveAll(List<T> objects) {
        return repository.saveAll(objects);
    }

    @Override
    public Optional<T> update(T object) {
        return Optional.empty();
    }
}
