package com.roconmachine.io.user_info.service;

import com.roconmachine.io.user_info.dto.UserDetails;
import org.keycloak.admin.client.Keycloak;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakService implements IKeycloakService{

    private Keycloak keycloak;
    @Autowired
    private ModelMapper modelMapper;
    public KeycloakService(Keycloak _Keycloak){
        this.keycloak = _Keycloak;
    }

    @Override
    public ResponseEntity<List<UserDetails>> getAllUsers() {
        List<UserDetails> list = this.keycloak.realm("web-app").users().list().stream().map(
                userRepresentation -> modelMapper.map(userRepresentation, UserDetails.class)
        ).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<UserDetails> getUserById(String userId) {
        UserDetails userDetails = modelMapper.map(this.keycloak.realm("web-app").users().
                        searchByUsername(userId, true).get(0),
                UserDetails.class);
        return ResponseEntity.ok(userDetails);
    }

    private String getToken(){
        return this.keycloak.tokenManager().getAccessTokenString();
    }
}
