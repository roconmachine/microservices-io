package com.roconmachine.io.fgaccess.service;

import com.roconmachine.io.fgaccess.entity.PropertiesEntity;
import com.roconmachine.io.fgaccess.repo.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;
    private final R2dbcEntityTemplate r2dbcTemplate;

    public Flux<PropertiesEntity> search(String uuid, String key) {
        Criteria criteria = null;

        if (uuid != null) {
            criteria = Criteria.where("uuid").like(uuid+"%");
        }

        if (key != null) {
            criteria = (criteria == null ? Criteria.where("key").is(key) : criteria.and("key").is(key));
        }

        return r2dbcTemplate
                .select(PropertiesEntity.class)
                .matching(Query.query(criteria == null ? Criteria.empty() : criteria))
                .all();
    }

    public Flux<PropertiesEntity> save(Flux<PropertiesEntity> properties, String prefix, Long parentId)
    {
        AtomicLong sequence = new AtomicLong(1);
        return properties
                .map(property -> {
                    // Generate the uuid based on the prefix, parentId, and incrementing sequence
                    String uuid = prefix + "_" + parentId + "_" + sequence.getAndIncrement();
                    property.setUuid(uuid); // Set the uuid for each property
                    return property;
                }).as(propertyRepository::saveAll);

    }

    public Mono<PropertiesEntity> getById(Long id){
        return propertyRepository.findById(id);
    }

    public Mono<Boolean> delete(Long id){
        return this.propertyRepository.existsById(id)
                .flatMap(existing -> {
                    if (existing) return this.propertyRepository.deleteById(id).then(Mono.just(true));
                    else return Mono.just(false);
                });
    }

    public Mono<PropertiesEntity> update(PropertiesEntity entity){
        return this.propertyRepository.existsById(entity.getId())
                .flatMap(aBoolean -> {
                    if(aBoolean) return this.propertyRepository.save(entity);
                    else return Mono.just(null);
                });
    }

}
