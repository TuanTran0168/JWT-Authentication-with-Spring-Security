package com.tuantran.jwt_authentication.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.tuantran.jwt_authentication.model.User;
import com.tuantran.jwt_authentication.service.AuthenticationService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public String authenticate(String email, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email,password);
        Authentication authentication = authenticationManager.authenticate(token);
        if(!authentication.isAuthenticated()) {
            throw new BadCredentialsException("Bad credentials");
        }
        User user = (User) authentication.getPrincipal();
        return jwtService.generateToken(user.getEmail(),user.getRole().getName());
    }
}
