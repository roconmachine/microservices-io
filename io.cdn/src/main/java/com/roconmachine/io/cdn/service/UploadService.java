package com.roconmachine.io.cdn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadService {
    @Value("${cdn.location}")
    public String LOCATION;


    public Mono<String> upload(Mono<FilePart> filePartMono) {
        // Ensure the upload directory exists
        Mono<Void> ensureDirectory = Mono.fromCallable(() -> {
            Path uploadDir = Paths.get(LOCATION);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
            return null;
        }).subscribeOn(Schedulers.boundedElastic()).then(); // Use bounded elastic for blocking operations

        // Handle single file upload
        return ensureDirectory.then(
                filePartMono.flatMap(filePart -> {
                    // Generate a unique filename
                    String newFilename = generateUniqueFilename(filePart.filename());
                    Path destination = Paths.get(LOCATION, newFilename);

                    // Save the file and return the filename
                    return filePart.transferTo(destination)
                            .thenReturn(newFilename);
                })
        );
    }

    private String generateUniqueFilename(String originalFilename) {
        String extension = "";
        int dotIndex = originalFilename.lastIndexOf(".");
        if (dotIndex > 0) {
            extension = originalFilename.substring(dotIndex);
        }
        return UUID.randomUUID() + "-" + Instant.now().toEpochMilli() + extension;
    }
}
