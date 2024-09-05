package com.roconmachine.io.identity_service.service;


import com.roconmachine.io.identity_service.config.ResponseMessagesConfig;
import com.roconmachine.io.identity_service.dto.AuthRequest;
import com.roconmachine.io.identity_service.dto.AuthResponse;
import com.roconmachine.io.identity_service.entity.UserCredential;
import com.roconmachine.io.identity_service.repository.UserCredentialRepository;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ModelMapper modelMapper;

    public AuthResponse saveUser(AuthRequest request) {
        if (alreadyExists(request.getName()))
            return AuthResponse.builder().code(ResponseMessagesConfig.ResponseStatus.DUPLICATE_USER.getCode()).
                    message(ResponseMessagesConfig.ResponseStatus.DUPLICATE_USER.getMessage()).build();
        UserCredential credential = modelMapper.map(request, UserCredential.class);
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        var user = repository.save(credential);
        return AuthResponse.builder().code(ResponseMessagesConfig.ResponseStatus.USER_CREATED.getCode()).
                message(ResponseMessagesConfig.ResponseStatus.USER_CREATED.getMessage()).build();
    }

    private boolean alreadyExists(String name){
        Optional<UserCredential> user = repository.findByName(name);
        return !user.isEmpty();
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}
