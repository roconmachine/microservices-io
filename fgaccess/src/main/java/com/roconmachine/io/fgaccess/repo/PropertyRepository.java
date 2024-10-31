package com.roconmachine.io.fgaccess.repo;

import com.roconmachine.io.fgaccess.entity.Properties;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PropertyRepository extends ReactiveCrudRepository<Properties, Long> {
    Flux<Properties> findByUuidStartingWith(String uuidPrefix);
    Mono<Properties> findByUuid(String uuidPrefix);
}
