package com.roconmachine.io.user_info.service;

import com.roconmachine.io.user_info.dto.UserDetails;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IKeycloakService {
    public ResponseEntity<List<UserDetails>> getAllUsers();
    public ResponseEntity<UserDetails> getUserById(String userId);
}
