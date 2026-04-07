package com.roconmachine.io.fgaccess.controllers;

import com.roconmachine.io.dataframe.fgaccess.interfaces.AuthzApi;
import com.roconmachine.io.fgaccess.converters.PolicyConverter;
import com.roconmachine.io.fgaccess.service.PolicyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/${service.name}/${service.apiversion}")
@RequiredArgsConstructor
public class AuthController implements AuthzApi {
    private final PolicyService service;
    @Override
    public Mono<ResponseEntity<Void>> authz(String subject_id, @Valid String objectid, @Valid String action_name, ServerWebExchange serverWebExchange) {
        return service.search(subject_id, action_name, objectid, true)
                .map(PolicyConverter::toModel)
                .collectList()
                .flatMap(actions -> {
                    if (actions.isEmpty()) {
                        return Mono.just(ResponseEntity.notFound().build());
                    }
                    // Return 200 OK with the converted roles as Flux
                    return Mono.just(ResponseEntity.ok().build());
                });
    }
}
