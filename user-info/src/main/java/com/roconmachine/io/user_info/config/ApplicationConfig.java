package com.roconmachine.io.user_info.config;

import lombok.AllArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ApplicationConfig {
    private final KeycloakConfig keycloakConfig;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public Keycloak generateKeycloakClient() {
        return KeycloakBuilder.builder()
                .serverUrl(this.keycloakConfig.getServerUrl())
                .clientId(this.keycloakConfig.getClientid())
                .clientSecret(this.keycloakConfig.getClientsecret())
                .realm(this.keycloakConfig.getRealm())
                .grantType(this.keycloakConfig.getGrantType())
                .build();
    }
}
