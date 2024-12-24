package com.tuantran.jwt_authentication.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    String authenticate(String email, String password);
}
