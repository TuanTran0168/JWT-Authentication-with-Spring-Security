package com.tuantran.jwt_authentication.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tuantran.jwt_authentication.model.User;
import com.tuantran.jwt_authentication.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUserByUsername = userRepository.findByEmail(username);
        if(optionalUserByUsername.isPresent()){
            return optionalUserByUsername.get();
        }else{
            throw new UsernameNotFoundException("username tidak ditemukan");
        }
    }
}
