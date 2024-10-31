package com.roconmachine.io.fgaccess.controllers;

import com.roconmachine.io.dataframe.access.interfaces.ResourcesApi;
import com.roconmachine.io.dataframe.access.models.Resource;
import com.roconmachine.io.fgaccess.service.PolicyMappingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("${service.name}/${service.apiversion}")
@AllArgsConstructor
public class ResourceController  implements ResourcesApi {

    private final PolicyMappingService service;
//    @GetMapping("/test")
//    public ResponseEntity<Flux<PolicyMappingEntity>> ping(){
//        PolicyMappingEntity policyMapping = PolicyMappingEntity.builder()
//                .action(ActionEnum.WRITE)
//                .recordStatus(RecordStatus.DELETED)
//                .resource_id(8L)
//                .build();
//        Flux<PolicyMappingEntity> response =  this.service.getPolicyMapping(policyMapping);
//        return new ResponseEntity(response, HttpStatus.OK);
//    }
    @Override
    public Mono<ResponseEntity<Flux<Resource>>> resourcesGet(ServerWebExchange serverWebExchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Void>> resourcesIdDelete(String s, ServerWebExchange serverWebExchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Resource>> resourcesIdGet(String s, ServerWebExchange serverWebExchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Void>> resourcesIdPut(String s, @Valid Mono<Resource> mono, ServerWebExchange serverWebExchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Void>> resourcesPost(@Valid Mono<Resource> mono, ServerWebExchange serverWebExchange) {
        return null;
    }
}