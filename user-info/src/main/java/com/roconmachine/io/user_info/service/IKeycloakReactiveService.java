package com.roconmachine.io.user_info.service;

import org.keycloak.representations.idm.UserRepresentation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IKeycloakReactiveService {
    public Flux<UserRepresentation> getAllUsers();
    public Mono<UserRepresentation> getUserById(String userId);

}
