package com.roconmachine.io.fgaccess.repo;

import com.roconmachine.io.fgaccess.entity.Condition;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ConditionRepository extends ReactiveCrudRepository<Condition, Long> {
}
