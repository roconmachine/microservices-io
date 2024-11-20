package com.roconmachine.io.fgaccess.controllers;


import com.roconmachine.io.dataframe.access.interfaces.PolicyMappingsApi;
import com.roconmachine.io.dataframe.access.models.PolicyMapping;
import com.roconmachine.io.fgaccess.converter.PolicyMappingConverter;
import com.roconmachine.io.fgaccess.domains.PolicyMappingDto;
import com.roconmachine.io.fgaccess.service.PolicyMappingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

        return  Mono.just(ResponseEntity.ok(flux)).onErrorResume(throwable -> Mono.just(ResponseEntity.notFound().build()));
    }

    @Override
    public Mono<ResponseEntity<Void>> policyMappingsIdDelete(Long id, ServerWebExchange serverWebExchange) {
        return service.deletePolicyMappingsId(id)
                .map(deleted -> deleted ? ResponseEntity.noContent().<Void>build():ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<PolicyMapping>> policyMappingsIdGet(Long id, ServerWebExchange serverWebExchange) {
        return service.getPolicyMappingById(id)
                .map(dto->PolicyMappingConverter.convertPolicyMapping(dto))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<PolicyMapping>> policyMappingsIdPut(Long id, @Valid Mono<PolicyMapping> mono, ServerWebExchange serverWebExchange) {

        return mono.map(policyMapping -> {
            return PolicyMappingConverter.convertPolicyMapping(policyMapping);
        }).flatMap(policyMappingDto -> {
            return service.update(policyMappingDto,id);
        }).map(policyMappingDto -> {
            return PolicyMappingConverter.convertPolicyMapping(policyMappingDto);
        }).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
//        return service.update(PolicyMappingConverter.convertPolicyMapping(s), id)
//                .map(dto ->PolicyMappingConverter.convertPolicyMapping(dto))
//                .map(ResponseEntity::ok)
//                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<PolicyMapping>> policyMappingsPost(@Valid Mono<PolicyMapping> mono, ServerWebExchange serverWebExchange) {

         return mono.map(policyMapping -> PolicyMappingConverter.convertPolicyMapping(policyMapping))
                         .flatMap(policyMappingDto -> service.savePolicyMapping(policyMappingDto))
                                 .map(savedPM -> PolicyMappingConverter.convertPolicyMapping(savedPM))
                                         .map(ResponseEntity::ok)
                 .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

}
