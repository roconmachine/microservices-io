package com.pai.app.state.repositories;

import com.pai.app.state.entities.DomainObjectStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainObjectStateRepository extends JpaRepository<DomainObjectStateEntity, Long> {
}
