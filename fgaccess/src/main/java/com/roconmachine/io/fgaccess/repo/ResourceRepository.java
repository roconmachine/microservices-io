package com.roconmachine.io.fgaccess.repo;

import com.roconmachine.io.fgaccess.entity.Resource;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ResourceRepository extends ReactiveCrudRepository<Resource, Long> {
}
