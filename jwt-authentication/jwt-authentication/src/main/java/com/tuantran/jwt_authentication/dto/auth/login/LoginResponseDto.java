package com.tuantran.jwt_authentication.dto.auth.login;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String email;
    private String token;
}
