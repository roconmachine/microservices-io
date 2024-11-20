package com.roconmachine.io.fgaccess.controllers;

import com.roconmachine.io.dataframe.fgaccess.interfaces.SubjectApi;
import com.roconmachine.io.dataframe.fgaccess.models.Subject;
import com.roconmachine.io.fgaccess.converters.ActionConverter;
import com.roconmachine.io.fgaccess.converters.SubjectConverter;
import com.roconmachine.io.fgaccess.entity.SubjectEntity;
import com.roconmachine.io.fgaccess.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/${service.name}/${service.apiversion}")
@RequiredArgsConstructor
public class SubjectController  implements SubjectApi {
    private final SubjectService service;
    @Override
    public Mono<ResponseEntity<Subject>> subjectPost(@Valid Mono<Subject> mono, ServerWebExchange serverWebExchange) {
        return mono.flatMap(subject ->
                        service.save(SubjectConverter.toEntity(subject)) // Save the entity using the service
                                .map(SubjectConverter::toModel)           // Convert saved entity to model
                )
                .map(ResponseEntity::ok)                                    // Wrap the model in ResponseEntity
                .onErrorResume(e -> {                                       // Handle errors
                    return Mono.just(ResponseEntity.badRequest().build());
                });
    }

    @Override
    public Mono<ResponseEntity<Void>> subjectSubjectIdIsActivePut(Long aLong, Boolean aBoolean, ServerWebExchange serverWebExchange) {
        return service.update(aLong, SubjectEntity.builder()
                .status(aBoolean)
                .build())
                .flatMap(entity -> {
                    if(entity != null){
                        return Mono.just(ResponseEntity.ok().build());
                    }else return Mono.just(ResponseEntity.badRequest().build());
                }) ;
    }
}
