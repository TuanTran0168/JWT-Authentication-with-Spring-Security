package com.tuantran.jwt_authentication.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuantran.jwt_authentication.dto.auth.AuthenticationResponse;
import com.tuantran.jwt_authentication.dto.auth.login.LoginRequestDto;
import com.tuantran.jwt_authentication.dto.auth.register.RegisterRequestDto;
import com.tuantran.jwt_authentication.dto.auth.register.RegisterResponseDto;
import com.tuantran.jwt_authentication.service.AuthenticationService;
import com.tuantran.jwt_authentication.service.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequestDto loginRequest) {
        String jwtToken = authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(AuthenticationResponse.builder().token(jwtToken).build());
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto){
        RegisterResponseDto response = userService.register(registerRequestDto);
        return ResponseEntity.ok(response);
    }

}
