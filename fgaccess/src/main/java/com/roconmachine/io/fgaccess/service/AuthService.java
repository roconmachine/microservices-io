package com.roconmachine.io.fgaccess.service;

import com.roconmachine.io.fgaccess.dto.AuthDto;
import com.roconmachine.io.fgaccess.dto.PolicyMappingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PolicyMappingService policyMappingService;
    public Mono<Boolean> checkAuth(AuthDto ar) {
        return policyMappingService.getPolicyMapping(PolicyMappingDto.builder()
                .subject_type(PolicyMappingDto.SubjectTypeEnum.USER)
                .subject_id(ar.getUserid())
                .resource_id(ar.getResourceId())
                .action(PolicyMappingDto.ActionEnum.fromValue(ar.getAction()))
                .recordStatus(PolicyMappingDto.RecordStatus.INSERTED)
                .build()
        ).collectList().map(list -> {return list.size() > 0 ? true : false;});
//        return Mono.just(true);
    }
}
