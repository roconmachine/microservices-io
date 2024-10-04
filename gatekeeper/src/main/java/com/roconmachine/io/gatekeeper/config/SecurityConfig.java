package com.roconmachine.io.gatekeeper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .cors(corsSpec -> corsSpec.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.addAllowedOrigin("http://localhost:4200");  // Allow specific origin
                    config.addAllowedMethod("*");  // Allow all HTTP methods
                    config.addAllowedHeader("*");  // Allow all headers
                    config.setAllowCredentials(true);  // Allow credentials like cookies or authorization headers
                    return config;
                }))
                .csrf(ServerHttpSecurity.CsrfSpec ::disable)

                .authorizeExchange(auth -> auth.
                        pathMatchers("/ping").permitAll()
                        .pathMatchers("/accounts/api/**").authenticated()

                        .anyExchange()
                        .authenticated()
                )

                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return ReactiveJwtDecoders.fromIssuerLocation("http://localhost:9090/realms/com-microservice-io");
    }

    @Bean
    public CorsWebFilter corsWebFilter() {
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.addAllowedOrigin("http://localhost:4200"); // Replace with your Angular app's URL
//        corsConfig.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
//        corsConfig.addAllowedHeader("*"); // Allow all headers
//        corsConfig.setAllowCredentials(true); // Allow credentials such as cookies
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig); // Apply CORS settings to all paths

        return null; //new CorsWebFilter(source);
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(false);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}

