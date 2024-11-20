package com.roconmachine.io.fgaccess.controllers;

import com.roconmachine.io.dataframe.fgaccess.interfaces.UserRoleApi;
import com.roconmachine.io.dataframe.fgaccess.models.UserRole;
import com.roconmachine.io.fgaccess.converters.PolicyConverter;
import com.roconmachine.io.fgaccess.converters.UserRoleConverter;
import com.roconmachine.io.fgaccess.core.BaseController;
import com.roconmachine.io.fgaccess.service.UserRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/${service.name}/${service.apiversion}")
@RequiredArgsConstructor
public class UserRoleActivity implements UserRoleApi {
    private final UserRoleService service;

    @Override
    public Mono<ResponseEntity<UserRole>> userRoleIdDelete(Long aLong, ServerWebExchange serverWebExchange) {
        return service.delete(aLong)
                .flatMap(
                        aBoolean -> {
                            if (aBoolean) return Mono.just(ResponseEntity.status(HttpStatus.OK).build());
                            else return Mono.just(ResponseEntity.notFound().build());
                        }
                );
    }

    @Override
    public Mono<ResponseEntity<UserRole>> userRolePost(@Valid Mono<UserRole> mono, ServerWebExchange serverWebExchange) {
        return mono.flatMap(userRole ->
                        service.save(UserRoleConverter.toEntity(userRole)) // Save the entity using the service
                                .map(UserRoleConverter::toModel)           // Convert saved entity to model
                )
                .map(ResponseEntity::ok)                                    // Wrap the model in ResponseEntity
                .onErrorResume(e -> {                                       // Handle errors
                    // Log the error if needed
                    return Mono.just(ResponseEntity.badRequest().build());
                });
    }
}
