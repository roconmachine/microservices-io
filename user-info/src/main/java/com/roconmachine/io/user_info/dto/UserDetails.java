package com.roconmachine.io.user_info.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetails {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    public static UserDetails getMapper(UserRepresentation userRepresentation) {
        return UserDetails.builder().username(userRepresentation.getUsername()).firstName(userRepresentation.getFirstName())
                .lastName(userRepresentation.getLastName()).email(userRepresentation.getEmail()).build();
    }
}
