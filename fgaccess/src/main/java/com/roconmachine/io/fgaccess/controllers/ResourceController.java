package com.roconmachine.io.fgaccess.controllers;

import com.roconmachine.io.dataframe.access.interfaces.ResourcesApi;
import com.roconmachine.io.dataframe.access.models.Resource;
import com.roconmachine.io.fgaccess.converter.ResourceConverter;
import com.roconmachine.io.fgaccess.service.PropertyService;
import com.roconmachine.io.fgaccess.service.ResourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
public class ResourceController implements ResourcesApi {

    private final PropertyService propertyService;
    private final ResourceService resourceService;
    private final ModelMapper modelMapper;


    @Override
    public Mono<ResponseEntity<Flux<Resource>>> resourcesGet(@Valid String name, @Valid String type, @Valid String source, @Valid String owner, ServerWebExchange serverWebExchange) {
        Flux<Resource> resourcesFlux = resourceService.search(name, type, source, owner)
                .map(ResourceConverter::convert);

        return resourcesFlux
                .hasElements()
                .flatMap(hasElements -> {
                    if (hasElements) {
                        return Mono.just(ResponseEntity.ok(resourcesFlux));
                    } else {
                        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).<Flux<Resource>>build());
                    }
                })
                .onErrorResume(throwable -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).<Flux<Resource>>build()));
    }

    @Override
    public Mono<ResponseEntity<Void>> resourcesIdDelete(Long aLong, ServerWebExchange serverWebExchange) {
        return resourceService.delete(aLong)
                .flatMap(deleted -> {
                    if (deleted) {
                        return this.propertyService.search("RES_" + aLong, null)
                                .flatMap(entity -> this.propertyService.delete(entity.getId()))
                                .then() // Wait for all property deletions to complete
                                .thenReturn(ResponseEntity.noContent().<Void>build());
                    } else {
                        return Mono.just(ResponseEntity.notFound().build());
                    }
                });
    }

    @Override
    public Mono<ResponseEntity<Resource>> resourcesIdGet(Long aLong, ServerWebExchange serverWebExchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Void>> resourcesIdPut(Long aLong, @Valid Mono<Resource> mono, ServerWebExchange serverWebExchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Resource>> resourcesPost(@Valid Mono<Resource> mono, ServerWebExchange serverWebExchange) {
        return mono
                .flatMap(resource ->
                         this.resourceService.save(ResourceConverter.convert(resource))
                                 .flatMap(res -> {
                                     return
                                             this.propertyService.save(Flux.fromIterable(res.getProperties()), "RES",res.getId()).
                                             then().
                                             thenReturn(res);

                                 })
                    )
                .map(resourceEntityMono -> ResourceConverter.convert(resourceEntityMono))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
