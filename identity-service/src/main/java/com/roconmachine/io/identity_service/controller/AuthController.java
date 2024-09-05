package com.roconmachine.io.identity_service.controller;



import com.roconmachine.io.identity_service.config.ResponseMessagesConfig;
import com.roconmachine.io.identity_service.dto.AuthRequest;
import com.roconmachine.io.identity_service.dto.AuthResponse;
import com.roconmachine.io.identity_service.dto.TokenResponse;
import com.roconmachine.io.identity_service.service.AuthService;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;



    @PostMapping("/register")
    public ResponseEntity<AuthResponse> addNewUser(@RequestBody AuthRequest user) {
        var response = service.saveUser(user);
        return new ResponseEntity<>(response,response.getCode() == ResponseMessagesConfig.ResponseStatus.USER_CREATED.getCode() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return new ResponseEntity<>(TokenResponse.builder().token(service.generateToken(authRequest.getName())).build(), HttpStatus.OK );
        } else {
            return new ResponseEntity<>(TokenResponse.builder().message(ResponseMessagesConfig.ResponseStatus.NOT_VALID_USER.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<AuthResponse> validateToken(@RequestParam("token") String token) {
        try {
            service.validateToken(token);
        }catch (SignatureException signatureException){
            return new ResponseEntity<>(AuthResponse.builder().
                    code(ResponseMessagesConfig.ResponseStatus.INVALID_TOKEN.getCode()).
                    message(ResponseMessagesConfig.ResponseStatus.INVALID_TOKEN.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(AuthResponse.builder().code(ResponseMessagesConfig.ResponseStatus.VALID_TOKEN.getCode()).message(ResponseMessagesConfig.ResponseStatus.VALID_TOKEN.getMessage()).build(), HttpStatus.OK);
    }

}
