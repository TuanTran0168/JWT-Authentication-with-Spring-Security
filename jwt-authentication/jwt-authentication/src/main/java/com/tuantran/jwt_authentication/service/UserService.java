package com.tuantran.jwt_authentication.service;

import com.tuantran.jwt_authentication.dto.auth.register.RegisterRequestDto;
import com.tuantran.jwt_authentication.dto.auth.register.RegisterResponseDto;

public interface UserService {

    RegisterResponseDto register(RegisterRequestDto registerRequestDto);

}
