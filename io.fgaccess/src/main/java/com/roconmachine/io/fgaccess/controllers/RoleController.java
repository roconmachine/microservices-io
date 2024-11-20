package com.roconmachine.io.fgaccess.controllers;

import com.roconmachine.io.dataframe.fgaccess.interfaces.RoleApi;
import com.roconmachine.io.dataframe.fgaccess.models.Role;
import com.roconmachine.io.fgaccess.converters.RoleConverter;
import com.roconmachine.io.fgaccess.entity.RoleEntity;
import com.roconmachine.io.fgaccess.service.RoleService;
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
public class RoleController implements RoleApi {
    private final RoleService roleService;


    @Override
    public Mono<ResponseEntity<Flux<Role>>> roleGet(@NotNull @Valid String s, ServerWebExchange serverWebExchange) {
        return roleService.search(s)
                .map(RoleConverter::toModel) // Convert RoleEntity to Role
                .collectList()               // Collect the roles into a List to check if empty
                .flatMap(roles -> {
                    if (roles.isEmpty()) {
                        return Mono.just(ResponseEntity.noContent().build()); // Return 204 No Content
                    }
                    // Return 200 OK with the converted roles as Flux
                    return Mono.just(ResponseEntity.ok(Flux.fromIterable(roles)));
                });
    }



    @Override
    public Mono<ResponseEntity<Void>> roleIdDelete(Long roleid, ServerWebExchange serverWebExchange) {
        return roleService.delete(roleid)
                .flatMap(
                        aBoolean -> {
                            if (aBoolean) return Mono.just(ResponseEntity.status(HttpStatus.OK).build());
                            else return Mono.just(ResponseEntity.notFound().build());
                        }
                );
    }

    @Override
    public Mono<ResponseEntity<Role>> roleIdGet(Long aLong, ServerWebExchange serverWebExchange) {
        return this.roleService.getById(aLong)
                .map(RoleConverter::toModel)
                .map(ResponseEntity::ok) // Directly return 200 OK for the role
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @Override
    public Mono<ResponseEntity<Role>> roleIdPut(Long aLong, @Valid Mono<Role> mono, ServerWebExchange serverWebExchange) {
        return mono.map(RoleConverter::toEntity)
                .flatMap(roleEntity -> roleService.update(aLong, roleEntity))
                .map(RoleConverter::toModel) // Convert the updated entity back to model
                .map(ResponseEntity::ok) // Return 200 OK with updated role
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Role>> rolePost(@Valid Mono<Role> mono, ServerWebExchange serverWebExchange) {
        return mono.flatMap(
                role ->
                    this.roleService.save(
                            RoleEntity.builder()
                                    .name(role.getName())
                                    .build()
                    )
                )
                .map(
                        roleEntity ->
                                Role.builder()
                                        .name(roleEntity.getName())
                                        .id(roleEntity.role_id)
                                        .build()
                )

                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
