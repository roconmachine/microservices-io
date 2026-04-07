package com.roconmachine.io.cdn.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class DownloadService
{
    @Value("${cdn.location}")
    public String LOCATION;
    private final ResourceLoader resourceLoader;

    public Mono<Resource> getFile(String fileName){
        return Mono
                .fromSupplier(()->resourceLoader
                .getResource("file:" + Paths.get(LOCATION + fileName)));

    }
}
