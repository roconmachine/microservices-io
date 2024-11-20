package com.roconmachine.io.fgaccess.controllers;

import com.roconmachine.io.dataframe.access.interfaces.AuthzApi;
import com.roconmachine.io.dataframe.access.models.AuthzRequest;
import com.roconmachine.io.dataframe.access.models.AuthzResponse;
import com.roconmachine.io.fgaccess.domains.AuthDto;
import com.roconmachine.io.fgaccess.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("${service.name}/${service.apiversion}")
@RequiredArgsConstructor
public class AuthController  implements AuthzApi {
    private final AuthService service;
    private final ModelMapper modelMapper;

    @Override
    public Mono<ResponseEntity<AuthzResponse>> authzCheckPost(@Valid Mono<AuthzRequest> mono, ServerWebExchange serverWebExchange) {


        return mono
                .map(authzRequest -> modelMapper.map(authzRequest, AuthDto.class)) // Convert to AuthzDto
                .flatMap(authzDto ->
                        service.checkAuth(authzDto)
                                .map(isAuthorized -> {
                                    return ResponseEntity.status(HttpStatus.OK)
                                            .body(AuthzResponse.builder().decision(isAuthorized).build());
                                })
                );


    }
}
