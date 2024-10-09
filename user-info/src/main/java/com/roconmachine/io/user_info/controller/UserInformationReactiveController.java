package com.roconmachine.io.user_info.controller;

import com.roconmachine.io.user_info.dto.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserInformationReactiveController implements IUserInformationReactive{


    @Override
    public Flux<UserDetails> getAllUsers() {
        return null;
    }

    @Override
    public Mono<UserDetails> getUserById(String id) {
        return null;
    }
}
