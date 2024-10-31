package com.roconmachine.io.fgaccess.converter;

import com.roconmachine.io.dataframe.access.models.PolicyMapping;
import com.roconmachine.io.fgaccess.dto.PolicyMappingDto;

public class PolicyMappingConverter {
    public static PolicyMapping convertPolicyMapping(PolicyMappingDto policyMappingDto){
        return PolicyMapping.builder()
                .id(policyMappingDto.getId())
                .policyId(policyMappingDto.getPolicy_id())
                .subjectId(policyMappingDto.getSubject_id())
                .subjectType(policyMappingDto.getSubject_type() != null ? PolicyMapping.SubjectTypeEnum.fromValue(policyMappingDto.getSubject_type().getValue()) : null)
                .resourceId(policyMappingDto.getResource_id())
                .action(policyMappingDto.getAction() != null? PolicyMapping.ActionEnum.fromValue(policyMappingDto.getAction().getValue()):null).build();
    }

    public static PolicyMappingDto convertPolicyMapping(PolicyMapping policyMapping){
        return PolicyMappingDto.builder()
                .policy_id(policyMapping.getPolicyId())
                .subject_type(PolicyMappingDto.SubjectTypeEnum.valueOf(policyMapping.getSubjectType().getValue()))
                .subject_id(policyMapping.getSubjectId())
                .action(PolicyMappingDto.ActionEnum.fromValue(policyMapping.getAction().getValue()))
                .resource_id(policyMapping.getResourceId())
                .build();
    }
}
