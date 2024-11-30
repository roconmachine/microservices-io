package com.pai.app.state.repositories;

import com.pai.app.state.entities.ActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<ActionEntity, Long> {
}
