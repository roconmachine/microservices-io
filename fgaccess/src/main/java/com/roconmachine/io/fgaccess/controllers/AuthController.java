package com.roconmachine.io.fgaccess.controllers;

import com.roconmachine.io.dataframe.access.interfaces.AuthzApi;
import com.roconmachine.io.dataframe.access.models.AuthzRequest;
import com.roconmachine.io.dataframe.access.models.AuthzResponse;
import com.roconmachine.io.fgaccess.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("${service.name}/${service.apiversion}")
@AllArgsConstructor
public class AuthController  implements AuthzApi {
    private final AuthService service;
    @Override
    public Mono<ResponseEntity<AuthzResponse>> authzCheckPost(@Valid Mono<AuthzRequest> mono, ServerWebExchange serverWebExchange) {
        Optional<AuthzRequest> authzRequest = mono.blockOptional();
        if(!authzRequest.isEmpty()){
            AuthzRequest ar = authzRequest.get();
            service.checkAuth(ar);
        }
        return null;
    }
}
