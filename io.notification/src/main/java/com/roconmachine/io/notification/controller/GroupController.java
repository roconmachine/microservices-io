package com.roconmachine.io.notification.controller;

import com.roconmachine.io.dataframe.notification.interfaces.GroupApi;
import com.roconmachine.io.dataframe.notification.models.Group;
import com.roconmachine.io.notification.converter.GroupConverter;
import com.roconmachine.io.notification.entities.GroupRecipientEntity;
import com.roconmachine.io.notification.services.GroupRecipientService;
import com.roconmachine.io.notification.services.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/${service.name}/${service.apiversion}")
@RequiredArgsConstructor
public class GroupController implements GroupApi {
    private final GroupService groupService;
    private final GroupConverter groupConverter;
    private final GroupRecipientService groupRecipientService;
    @Override
    public Mono<ResponseEntity<Void>> assignRecipientsInGroup(String groupName, @Valid Flux<Long> flux, ServerWebExchange serverWebExchange) {
        return groupService.getByName(groupName)
                .map(groupEntity ->
                    flux.map(aLong -> {
                        GroupRecipientEntity groupRecipientEntity = new GroupRecipientEntity();
                        groupRecipientEntity.setGroupName(groupEntity.getName());
                        groupRecipientEntity.setRecipientId(aLong);
                        return groupRecipientService.save(groupRecipientEntity);

                    }).onErrorResume(
                            throwable -> {
                                return Mono.empty();
                            }
                    )
                ).then(Mono.just(ResponseEntity.status(HttpStatus.OK).build()))
                ;

    }

    @Override
    public Mono<ResponseEntity<Void>> deleteGroup(String s, ServerWebExchange serverWebExchange) {
        return groupService.delete(s)
                .then(Mono.just(ResponseEntity.status(HttpStatus.OK).build()))
                ;
    }


    @Override
    public Mono<ResponseEntity<Void>> saveGroup(@Valid Flux<Group> flux, ServerWebExchange serverWebExchange) {
        return flux
                .map(groupConverter::toEntity)
                .flatMap(groupService::save)
                .map(groupConverter::toModel)
                .collectList()
                .flatMap(savedGroup -> {
                    if (savedGroup.isEmpty()) {
                        return Mono.just(ResponseEntity.noContent().build());
                    }
                    return Mono.just(ResponseEntity.status(HttpStatus.CREATED)
                            .build());
                });
    }
}
