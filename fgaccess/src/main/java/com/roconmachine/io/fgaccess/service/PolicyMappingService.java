package com.roconmachine.io.fgaccess.service;

import com.roconmachine.io.fgaccess.dto.PolicyMappingDto;
import com.roconmachine.io.fgaccess.entity.PolicyMappingEntity;
import com.roconmachine.io.fgaccess.repo.PolicyMappingRepositoryImp;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class PolicyMappingService {
    private PolicyMappingRepositoryImp repository;
    private ModelMapper modelMapper;
    public Flux<PolicyMappingDto> getPolicyMapping(PolicyMappingDto policyMapping){

        return repository.search(modelMapper.map(policyMapping, PolicyMappingEntity.class))
                .map(entity -> modelMapper.map(entity, PolicyMappingDto.class));
    }


}
