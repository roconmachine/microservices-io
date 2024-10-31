package com.roconmachine.io.fgaccess.repo;

import com.roconmachine.io.fgaccess.entity.Policy;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PolicyRepository extends ReactiveCrudRepository<Policy, Long> {
}
