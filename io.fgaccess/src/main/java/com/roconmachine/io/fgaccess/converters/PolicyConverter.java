package com.roconmachine.io.fgaccess.converters;

import com.roconmachine.io.dataframe.fgaccess.models.Policy;
import com.roconmachine.io.fgaccess.entity.PolicyEntity;

public class PolicyConverter {

    public static Policy toModel(PolicyEntity entity){
        Policy policy = new Policy();
        policy.setSubjectId(entity.subject_id);
        policy.setObjectId(entity.object_id);
        policy.actionName(entity.action_name);
        policy.setId(entity.policy_id);
        policy.status(entity.status);
        return policy;

    }

    public static PolicyEntity toEntity(Policy policy){
        PolicyEntity entity = new PolicyEntity();
        entity.subject_id = policy.getSubjectId() != null ? policy.getSubjectId(): null;
        entity.action_name = policy.getActionName() != null ? policy.getActionName(): null ;
        entity.object_id = policy.getObjectId() != null ? policy.getObjectId(): null;
        entity.status = policy.getStatus() != null ? policy.getStatus() : null;
        return entity;
    }
}
