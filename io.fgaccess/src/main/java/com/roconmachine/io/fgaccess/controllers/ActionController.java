package com.roconmachine.io.fgaccess.controllers;

import com.roconmachine.io.dataframe.fgaccess.interfaces.ActionApi;
import com.roconmachine.io.dataframe.fgaccess.models.Action;
import com.roconmachine.io.fgaccess.converters.ActionConverter;
import com.roconmachine.io.fgaccess.converters.RoleConverter;
import com.roconmachine.io.fgaccess.service.ActionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
public class ActionController implements ActionApi {
    private final ActionService service;

    @Override
    public Mono<ResponseEntity<Flux<Action>>> actionGet(@Valid String s,@Valid String type, ServerWebExchange serverWebExchange) {
        return service.search(s,type)
                .map(ActionConverter::toModel) // Convert RoleEntity to Role
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
    public Mono<ResponseEntity<Void>> actionIdDelete(Long actionid, ServerWebExchange serverWebExchange) {
        return service.delete(actionid)
                .flatMap(
                        aBoolean -> {
                            if (aBoolean) return Mono.just(ResponseEntity.status(HttpStatus.OK).build());
                            else return Mono.just(ResponseEntity.notFound().build());
                        }
                );
    }

    @Override
    public Mono<ResponseEntity<Action>> actionIdGet(Long aLong, ServerWebExchange serverWebExchange) {
        return this.service.getById(aLong)
                .map(ActionConverter::toModel)
                .map(ResponseEntity::ok) // Directly return 200 OK for the role
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @Override
    public Mono<ResponseEntity<Action>> actionIdPut(Long aLong, @Valid Mono<Action> mono, ServerWebExchange serverWebExchange) {
        return mono.map(ActionConverter::toEntity)
                .flatMap(roleEntity -> service.update(aLong, roleEntity))
                .map(ActionConverter::toModel) // Convert the updated entity back to model
                .map(ResponseEntity::ok) // Return 200 OK with updated role
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Action>> actionPost(@Valid Mono<Action> mono, ServerWebExchange serverWebExchange) {
        return mono.flatMap(action ->
                        service.save(ActionConverter.toEntity(action)) // Save the entity using the service
                                .map(ActionConverter::toModel)           // Convert saved entity to model
                )
                .map(ResponseEntity::ok)                                    // Wrap the model in ResponseEntity
                .onErrorResume(e -> {                                       // Handle errors
                    // Log the error if needed
                    return Mono.just(ResponseEntity.badRequest().build());
                });
    }
}
