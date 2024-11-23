package com.roconmachine.io.cdn.controller;


import com.roconmachine.io.cdn.service.UploadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/${service.name}/${service.apiversion}")
@RequiredArgsConstructor
public class UploadController  {
    public final UploadService service;

    @GetMapping("/testing")
    public String testing(){
        return "Hello World";
    }
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<String>> uploadFiles(@RequestPart("file") @Valid Mono<FilePart> filePart)  {

        return service.upload(filePart)
                .map(ResponseEntity::ok);
    }

}
