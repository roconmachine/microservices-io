package com.roconmachine.io.fgaccess.repo;

import com.roconmachine.io.fgaccess.entity.PolicyMappingEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@Service
public class PolicyMappingSearch {
    final R2dbcEntityTemplate r2dbcTemplate;
    public Flux<PolicyMappingEntity> search(PolicyMappingEntity policyMapping){

        Criteria criteria = Criteria.empty();
        if(policyMapping.getAction() != null)
            criteria = Criteria
                .where("action")
                .is(policyMapping.getAction().getValue());
        if(policyMapping.getRecordStatus() != null)
            criteria= criteria.and(Criteria.where("record_status").is(policyMapping.getRecordStatus().getValue()));
        if (policyMapping.getPolicy_id() != null)
            criteria= criteria.and(Criteria.where("policy_id").is(policyMapping.getPolicy_id()));
        if(policyMapping.getResource_id() != null)
            criteria= criteria.and(Criteria.where("resource_id").is(policyMapping.getResource_id()));
        if(policyMapping.getSubject_type() != null)
            criteria= criteria.and(Criteria.where("subject_type").is(policyMapping.getSubject_type().getValue()));
        if(policyMapping.getSubject_id() != null)
            criteria= criteria.and(Criteria.where("subject_id").is(policyMapping.getSubject_id()));
        return r2dbcTemplate
                .select(PolicyMappingEntity.class)
                .matching(Query.query(criteria))
                .all();

    }

}
