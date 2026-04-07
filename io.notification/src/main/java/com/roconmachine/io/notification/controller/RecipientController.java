package com.roconmachine.io.notification.controller;

import com.roconmachine.io.dataframe.notification.interfaces.RecipientApi;
import com.roconmachine.io.dataframe.notification.models.Recipient;
import com.roconmachine.io.notification.converter.RecipientConverter;
import com.roconmachine.io.notification.services.RecipientService;
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

import java.util.List;

@RestController
@RequestMapping("/${service.name}/${service.apiversion}")
@RequiredArgsConstructor
public class RecipientController implements RecipientApi {

    private final RecipientService recipientService;
    private final RecipientConverter recipientConverter;

    @Override
    public Mono<ResponseEntity<Void>> delete(@NotNull @Valid List<Long> list, ServerWebExchange serverWebExchange) {
        return Flux.fromIterable(list)
                .map(
                        aLong ->
                            recipientService.delete(aLong)
                                    .onErrorResume(
                                            throwable -> {
                                                return Mono.empty();
                                            }
                                    )

                ).collectList()
                .flatMap(savedRecipients -> {
                    if (savedRecipients.isEmpty()) {
                        return Mono.just(ResponseEntity.noContent().build());
                    }
                    return Mono.just(ResponseEntity.status(HttpStatus.OK)
                            .build());
                });
    }

    @Override
    public Mono<ResponseEntity<Void>> save(@Valid Flux<Recipient> flux, ServerWebExchange serverWebExchange) {
        return flux
                .map(recipientConverter::toEntity)
                .flatMap(entity ->
                        recipientService.save(entity)
                                .onErrorResume(e -> {

                                    return Mono.empty();
                                })
                )
                .map(recipientConverter::toModel)
                .collectList()
                .flatMap(savedRecipients -> {
                    if (savedRecipients.isEmpty()) {
                        return Mono.just(ResponseEntity.noContent().build());
                    }
                    return Mono.just(ResponseEntity.status(HttpStatus.CREATED)
                            .build());
                });
//                .then()
//                .onErrorResume(e -> {
//
//                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                            .build());
//                });
    }
}
