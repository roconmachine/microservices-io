package com.roconmachine.io.notification.controller;


import com.roconmachine.io.dataframe.notification.interfaces.NotificationApi;
import com.roconmachine.io.dataframe.notification.models.Notification;
import com.roconmachine.io.notification.converter.EmailNotificaitonConverter;
import com.roconmachine.io.notification.services.EmailNotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/${service.name}/${service.apiversion}")
@RequiredArgsConstructor
public class NotificationController implements NotificationApi {
    private final EmailNotificationService emailNotificationService;
    private final EmailNotificaitonConverter emailNotificaitonConverter;

    @Override
    public Mono<ResponseEntity<Flux<Notification>>> searchNotification(@Valid String status, @Valid String severity, ServerWebExchange serverWebExchange) {

        if (status == null & severity == null)
            return Mono.just(ResponseEntity.badRequest().build());
        return Mono.just(
                        ResponseEntity.ok(
                                emailNotificationService.search(status, severity)
                                        .map(emailNotificaitonConverter::toModel)

                        )
                )
                .onErrorResume(e -> {

                    return Mono.just(ResponseEntity.internalServerError().build());
                })
                .log();

    }

    @Override
    public Mono<ResponseEntity<Void>> setStatus(Long id, String status, ServerWebExchange serverWebExchange) {

        return this.emailNotificationService.getById(id)
                .flatMap(emailNotificationEntity -> {
                    emailNotificationEntity.setStatus(status);
                    return this.emailNotificationService.save(emailNotificationEntity) // Save is now part of the chain
                            .then(Mono.just(ResponseEntity.ok().build())); // Return an empty ResponseEntity on success
                });
    }

    @Override
    public Mono<ResponseEntity<Notification>> getById(Long aLong, ServerWebExchange serverWebExchange) {
        return emailNotificationService.getById(aLong)
                .map(emailNotificaitonConverter::toModel)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.internalServerError().build()))
                .log();
    }

    @Override
    public Mono<ResponseEntity<Notification>> saveNotification(@Valid Mono<Notification> mono, ServerWebExchange serverWebExchange) {
        return mono
                .map(emailNotificaitonConverter::toEntity)
                .flatMap(emailNotificationService::save)
                .map(emailNotificaitonConverter::toModel)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.internalServerError().build()))
                .log();
    }
}
