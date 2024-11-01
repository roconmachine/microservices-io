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


    public Mono<Boolean> deletePolicyMappingsId(Long id) {
        return policyMappingRepository.existsById(id)
                .flatMap(existing -> {
                    if (existing) return policyMappingRepository.deleteById(id).then(Mono.just(true));
                    else return Mono.just(false);
                });
    }

    public Mono<PolicyMappingDto> updatePolicyMappingsId(PolicyMappingDto dto) {
        return policyMappingRepository.save(this.modelMapper.map(dto, PolicyMappingEntity.class))
                .map(entity -> modelMapper.map(entity, PolicyMappingDto.class));
    }

    public Mono<PolicyMappingDto> getPolicyMappingById(Long id) {
        return policyMappingRepository.findById(id)
                .map(entity -> modelMapper.map(entity, PolicyMappingDto.class)); // Convert to DTO
    }

    public Mono<PolicyMappingDto> update(PolicyMappingDto policyMappingDto, Long id){
        policyMappingDto.setRecordStatus(PolicyMappingDto.RecordStatus.INSERTED);
        return policyMappingRepository.findById(id)
                .flatMap(entity -> {
                    this.modelMapper.map(policyMappingDto, entity);
                    entity.setId(id);
                    return this.policyMappingRepository.save(entity);
                })
                .map(updatedEntity -> this.modelMapper.map(updatedEntity, PolicyMappingDto.class));
    }

}
