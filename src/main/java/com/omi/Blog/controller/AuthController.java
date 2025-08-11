package com.omi.Blog.controller;

import com.omi.Blog.Model.Dto.AuthResponse;
import com.omi.Blog.Model.Dto.LoginRequest;
import com.omi.Blog.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.tokens.ValueToken;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService service;

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        UserDetails userDetails = service.authenticate(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        String tokenValue = service.genToken(userDetails);
        AuthResponse build = AuthResponse.builder()
                .token(tokenValue)
                .expiresIn(86400000)
                .build();

        return new ResponseEntity<>(build , HttpStatus.CREATED);

    }


}
