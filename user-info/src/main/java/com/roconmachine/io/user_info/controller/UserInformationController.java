package com.roconmachine.io.user_info.controller;

import com.roconmachine.io.user_info.config.KeycloakConfig;
import com.roconmachine.io.user_info.dto.UserDetails;
import com.roconmachine.io.user_info.service.IKeycloakService;
import com.roconmachine.io.user_info.service.KeycloakReactiveService;
import org.keycloak.admin.client.Keycloak;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserInformationController implements IUserInformation{
    private final IKeycloakService service;

    @Autowired
    private ModelMapper modelMapper;
    public UserInformationController(IKeycloakService _service) {
        this.service = _service;
    }

    @Override
    public ResponseEntity<List<UserDetails>> getAllUsers() {
        return this.service.getAllUsers();
    }

    @Override
    public ResponseEntity<UserDetails> getUserById(String id) {
        return this.service.getUserById(id);
    }
}
