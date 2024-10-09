package com.roconmachine.io.user_info.controller;

import com.roconmachine.io.user_info.dto.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserInformationReactive {
    @GetMapping("users")
    public Flux<UserDetails> getAllUsers();

    @GetMapping("user/{id}")
    public Mono<UserDetails> getUserById(@PathVariable String id);
}
