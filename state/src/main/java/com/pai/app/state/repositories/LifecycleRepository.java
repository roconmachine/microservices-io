package com.pai.app.state.repositories;

import com.pai.app.state.entities.LifecycleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LifecycleRepository extends JpaRepository<LifecycleEntity, Long> {

    List<LifecycleEntity> findAllByFromState(Long stateId);
}
