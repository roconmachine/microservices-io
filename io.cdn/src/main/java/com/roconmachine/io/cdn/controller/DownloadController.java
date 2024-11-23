package com.roconmachine.io.cdn.controller;

import com.roconmachine.io.cdn.service.DownloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/${service.name}/${service.apiversion}")
@RequiredArgsConstructor
public class DownloadController {
    private final DownloadService service;

    @GetMapping("/download")
    public Mono<ResponseEntity<Resource>> downloadIdGet(@RequestParam("file") String s, ServerWebExchange serverWebExchange) {
        return service.getFile(s) // Call the service to fetch the video as a Resource
                .map(resource -> {
                    // Add appropriate headers for the download response
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                    headers.setContentDisposition(ContentDisposition.builder("attachment")
                            .filename(resource.getFilename())
                            .build());
                    return ResponseEntity.ok()
                            .headers(headers)
                            .body(resource);
                })
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build())) // Handle the case where the video isn't found
                .onErrorResume(e -> {
                    // Handle errors gracefully, return an appropriate response
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(null));
                });
    }

}
