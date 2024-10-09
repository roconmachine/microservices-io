package com.roconmachine.io.user_info.controller;

import com.roconmachine.io.user_info.dto.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RequestMapping("${service.name}/${service.apiversion}/reactive")
public interface IUserInformationReactive {
    @GetMapping("/users")
    public Flux<UserDetails> getAllUsers();

    @GetMapping("/users/{id}")
    public Mono<UserDetails> getUserById(@PathVariable String id);
}
