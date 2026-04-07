package com.roconmachine.io.fgaccess.service;

import com.roconmachine.io.fgaccess.entity.ResourceEntity;
import com.roconmachine.io.fgaccess.repo.ResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ResourceService extends AbstructService<ResourceEntity, ResourceRepository>{
    private final ResourceRepository resourceRepository;
    private final R2dbcEntityTemplate r2dbcTemplate;

    public Mono<ResourceEntity> save(ResourceEntity entity){return this.resourceRepository.save(entity);}

    public Flux<ResourceEntity> search(String name, String type, String source, String owner) {
        Criteria criteria = null;

        if (name != null) {
            criteria = Criteria.where("name").like("%"+name+"%");
        }

        if (type != null) {
            criteria = (criteria == null ? Criteria.where("type").is(type) : criteria.and("type").is(type));
        }
        if (source != null) {
            criteria = (criteria == null ? Criteria.where("source").is(source) : criteria.and("source").is(source));
        }
        if (owner != null) {
            criteria = (criteria == null ? Criteria.where("owner").is(owner) : criteria.and("owner").is(owner));
        }

        return r2dbcTemplate
                .select(ResourceEntity.class)
                .matching(Query.query(criteria == null ? Criteria.empty() : criteria))
                .all();
    }
}
