package com.roconmachine.io.fgaccess.controllers;


import com.roconmachine.io.dataframe.access.interfaces.PolicyMappingsApi;
import com.roconmachine.io.dataframe.access.models.PolicyMapping;
import com.roconmachine.io.fgaccess.converter.PolicyMappingConverter;
import com.roconmachine.io.fgaccess.dto.PolicyMappingDto;
import com.roconmachine.io.fgaccess.service.PolicyMappingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("${service.name}/${service.apiversion}")
@RequiredArgsConstructor
public class PolicyMappingController implements PolicyMappingsApi {

    private final PolicyMappingService service;
    private final ModelMapper modelMapper;

    @Override
    public Mono<ResponseEntity<Flux<PolicyMapping>>> policyMappingsGet(@Valid Long policyId, @Valid Long subjectId, @Valid Long resourceId, @Valid String subjectType, @Valid String action, @Valid String recordStatus, ServerWebExchange serverWebExchange) {
        Flux<PolicyMapping> flux = service.getPolicyMapping(PolicyMappingDto.builder()
                .policy_id(policyId)
                .subject_id(subjectId)
                .resource_id(resourceId)
                .subject_type(subjectType != null ? PolicyMappingDto.SubjectTypeEnum.valueOf(subjectType):null)
                .action(action != null ?  PolicyMappingDto.ActionEnum.fromValue(action):null)
                .recordStatus(recordStatus != null ? PolicyMappingDto.RecordStatus.fromValue(recordStatus) : null)
                .build())
                  .map(entity -> PolicyMappingConverter.convertPolicyMapping(entity));

        return  Mono.just(ResponseEntity.ok(flux));
    }

    @Override
    public Mono<ResponseEntity<Void>> policyMappingsIdDelete(UUID uuid, ServerWebExchange serverWebExchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<PolicyMapping>> policyMappingsIdGet(UUID uuid, ServerWebExchange serverWebExchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<PolicyMapping>> policyMappingsIdPut(UUID uuid, @Valid Mono<PolicyMapping> mono, ServerWebExchange serverWebExchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<PolicyMapping>> policyMappingsPost(@Valid Mono<PolicyMapping> mono, ServerWebExchange serverWebExchange) {

        return null;
    }
}
