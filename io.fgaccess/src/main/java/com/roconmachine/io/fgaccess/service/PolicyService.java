package com.roconmachine.io.fgaccess.service;

import com.roconmachine.io.fgaccess.core.AbstructService;
import com.roconmachine.io.fgaccess.entity.ActionEntity;
import com.roconmachine.io.fgaccess.entity.PolicyEntity;
import com.roconmachine.io.fgaccess.repository.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PolicyService extends AbstructService<PolicyEntity, PolicyRepository> {
    private final R2dbcEntityTemplate r2dbcTemplate;
    private final PolicyRepository repository;


    public Flux<PolicyEntity> search(String subjectid, String actionname, String objectid, Boolean status){
        Criteria criteria = null;
        if(subjectid != null)
            criteria = Criteria
                    .where("subject_id")
                    .like("%"+subjectid+"%");
        if (actionname != null)
            criteria = criteria == null ? Criteria.where("action_name").like("%" + actionname + "%"):
                    criteria.and("action_name").like("%" + actionname + "%");

        if (objectid != null)
            criteria = criteria == null ? Criteria.where("object_id").like("%" + objectid + "%"):
                    criteria.and("object_id").like("%" + objectid + "%");

        if (status != null)
            criteria = criteria == null ? Criteria.where("status").is(status):
                    criteria.and("status").is(status);

        return r2dbcTemplate
                .select(PolicyEntity.class)
                .matching(Query.query(criteria))
                .all();
    }

    public Mono<PolicyEntity> update(Long id, PolicyEntity entity){
        return this.repository.findById(id)
                .flatMap(
                        policyEntity -> {

                            policyEntity.subject_id = entity.subject_id;
                            policyEntity.action_name = entity.action_name;
                            policyEntity.object_id = entity.object_id;
                            policyEntity.status = entity.status;
                            return this.repository.save(entity);
                        }
                );
    }
}
