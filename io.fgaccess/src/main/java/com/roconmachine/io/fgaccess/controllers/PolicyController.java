package com.roconmachine.io.fgaccess.controllers;

import com.roconmachine.io.dataframe.fgaccess.interfaces.PolicyApi;
import com.roconmachine.io.dataframe.fgaccess.models.Policy;
import com.roconmachine.io.dataframe.fgaccess.models.Role;
import com.roconmachine.io.fgaccess.converters.ActionConverter;
import com.roconmachine.io.fgaccess.converters.PolicyConverter;
import com.roconmachine.io.fgaccess.service.PolicyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/${service.name}/${service.apiversion}")
@RequiredArgsConstructor
public class PolicyController implements PolicyApi {
    private final PolicyService service;
    @Override
    public Mono<ResponseEntity<Flux<Policy>>> policyGet(@Valid String subject_id, @Valid String action, @Valid String object_id, @Valid Boolean aBoolean, ServerWebExchange serverWebExchange) {
        return service.search(subject_id, action, object_id, aBoolean)
                .map(PolicyConverter::toModel) // Convert RoleEntity to Role
                .collectList()               // Collect the roles into a List to check if empty
                .flatMap(actions -> {
                    if (actions.isEmpty()) {
                        return Mono.just(ResponseEntity.noContent().build()); // Return 204 No Content
                    }
                    // Return 200 OK with the converted roles as Flux
                    return Mono.just(ResponseEntity.ok(Flux.fromIterable(actions)));
                });
    }

    @Override
    public Mono<ResponseEntity<Policy>> policyIdDelete(Long aLong, ServerWebExchange serverWebExchange) {
        return service.delete(aLong)
                .flatMap(
                        aBoolean -> {
                            if (aBoolean) return Mono.just(ResponseEntity.status(HttpStatus.OK).build());
                            else return Mono.just(ResponseEntity.notFound().build());
                        }
                );
    }

    @Override
    public Mono<ResponseEntity<Policy>> policyIdGet(Long aLong, ServerWebExchange serverWebExchange) {
        return this.service.getById(aLong)
                .map(PolicyConverter::toModel)
                .map(ResponseEntity::ok) // Directly return 200 OK for the role
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Policy>> policyIdPut(Long aLong, @Valid Mono<Policy> mono, ServerWebExchange serverWebExchange) {
        return mono.map(PolicyConverter::toEntity)
                .flatMap(roleEntity -> service.update(aLong, roleEntity))
                .map(PolicyConverter::toModel) // Convert the updated entity back to model
                .map(ResponseEntity::ok) // Return 200 OK with updated role
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @Override
    public Mono<ResponseEntity<Policy>> policyPost(@Valid Mono<Policy> mono, ServerWebExchange serverWebExchange) {
        return mono.flatMap(policy ->
                        service.save(PolicyConverter.toEntity(policy)) // Save the entity using the service
                                .map(PolicyConverter::toModel)           // Convert saved entity to model
                )
                .map(ResponseEntity::ok)                                    // Wrap the model in ResponseEntity
                .onErrorResume(e -> {                                       // Handle errors
                    // Log the error if needed
                    return Mono.just(ResponseEntity.badRequest().build());
                });
    }
}
