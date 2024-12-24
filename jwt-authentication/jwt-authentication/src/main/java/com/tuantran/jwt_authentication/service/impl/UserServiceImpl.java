package com.tuantran.jwt_authentication.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tuantran.jwt_authentication.dto.auth.register.RegisterRequestDto;
import com.tuantran.jwt_authentication.dto.auth.register.RegisterResponseDto;
import com.tuantran.jwt_authentication.exception.UserNotFoundException;
import com.tuantran.jwt_authentication.model.Role;
import com.tuantran.jwt_authentication.model.User;
import com.tuantran.jwt_authentication.repository.RoleRepository;
import com.tuantran.jwt_authentication.repository.UserRepository;
import com.tuantran.jwt_authentication.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    @Override
    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
        Optional<Role> optionalRole = roleRepository.findById(registerRequestDto.getRoleId());

        User savedUser;
        if(optionalRole.isPresent()){
            Role role = optionalRole.get();
            User user = new User();
            user.setEmail(registerRequestDto.getEmail());
            user.setUsername(registerRequestDto.getEmail());
            String userPassword = registerRequestDto.getPassword();
            user.setPassword(bCryptPasswordEncoder.encode(userPassword));
            user.setRole(role);
            savedUser = userRepository.save(user);
        }else{
            throw new UserNotFoundException("User not found");
        }

        return RegisterResponseDto.builder()
                .email(savedUser.getEmail())
                .role(savedUser.getRole().getName())
                .build();
    }
}
