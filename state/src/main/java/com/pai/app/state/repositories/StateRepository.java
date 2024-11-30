package com.pai.app.state.repositories;

import com.pai.app.state.entities.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<StateEntity, Long> {

    List<StateEntity> findAllByIsRootAndAppName(boolean isRoot, String appName);
}
