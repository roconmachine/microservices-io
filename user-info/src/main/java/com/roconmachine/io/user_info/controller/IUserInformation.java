package com.roconmachine.io.user_info.controller;

import com.roconmachine.io.user_info.dto.UserDetails;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@RequestMapping("${service.name}/${service.apiversion}")
public interface IUserInformation {
    @GetMapping("/users")
    public ResponseEntity<List<UserDetails>> getAllUsers();

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDetails> getUserById(@Valid @PathVariable String id);
}
