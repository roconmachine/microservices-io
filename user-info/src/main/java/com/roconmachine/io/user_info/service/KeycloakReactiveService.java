package com.roconmachine.io.user_info.service;

import com.roconmachine.io.user_info.config.KeycloakConfig;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class KeycloakReactiveService implements IKeycloakReactiveService{
    private final WebClient webClient;
    private final KeycloakConfig keycloakConfig;

    public KeycloakReactiveService(WebClient.Builder webClientBuilder, KeycloakConfig keycloakConfig) {
        this.keycloakConfig = keycloakConfig;
        this.webClient = webClientBuilder.baseUrl(this.keycloakConfig.getServerUrl()).build();

    }


    public Flux<UserRepresentation> getAllUsers() {
        return getAccessToken()
                .flatMapMany(token -> webClient
                        .get()
                        .uri("/admin/realms/{realm}/users", this.keycloakConfig.getRealm())
                        .header("Authorization", "Bearer " + token)
                        .retrieve()
                        .bodyToFlux(UserRepresentation.class)
                ).switchIfEmpty(
                        Flux.empty()
                );
    }

    public Mono<UserRepresentation> getUserById(String userId) {


        return getAccessToken()
                .flatMap(token -> webClient
                        .get()
                        .uri("/admin/realms/{realm}/users/{id}", this.keycloakConfig.getRealm(), userId)
                        .header("Authorization", "Bearer " + token)
                        .retrieve()
                        .bodyToMono(UserRepresentation.class)
                );
    }

    public Mono<String> getAccessToken() {
        return webClient
                .post()
                .uri("/realms/{realm}/protocol/openid-connect/token", this.keycloakConfig.getRealm())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .bodyValue("client_id=" + this.keycloakConfig.getClientid() + "&client_secret=" + this.keycloakConfig.getClientsecret() + "&grant_type=" + this.keycloakConfig.getGrantType())
                .retrieve()
                .bodyToMono(KeycloakTokenResponse.class)
                .map(KeycloakTokenResponse::getAccessToken);
    }

    static class KeycloakTokenResponse {
        private String access_token;

        public String getAccessToken() {
            return access_token;
        }

        public void setAccessToken(String access_token) {
            this.access_token = access_token;
        }
    }

}
