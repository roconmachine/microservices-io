package com.roconmachine.io.fgaccess.service;

import com.roconmachine.io.fgaccess.dto.PolicyMappingDto;
import com.roconmachine.io.fgaccess.entity.PolicyMappingEntity;
import com.roconmachine.io.fgaccess.repo.PolicyMappingRepository;
import com.roconmachine.io.fgaccess.repo.PolicyMappingSearch;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PolicyMappingService {
    private final PolicyMappingSearch repository;
    private final ModelMapper modelMapper;
    private final PolicyMappingRepository policyMappingRepository;
    public Flux<PolicyMappingDto> getPolicyMapping(PolicyMappingDto policyMapping){

        return repository.search(modelMapper.map(policyMapping, PolicyMappingEntity.class))
                .map(entity -> modelMapper.map(entity, PolicyMappingDto.class));
    }


    public Mono<PolicyMappingDto> savePolicyMapping(PolicyMappingDto policyMapping){
        policyMapping.setRecordStatus(PolicyMappingDto.RecordStatus.INSERTED);
        return policyMappingRepository.save(this.modelMapper.map(policyMapping, PolicyMappingEntity.class))
                .map(entity -> modelMapper.map(entity, PolicyMappingDto.class));
    }



}
