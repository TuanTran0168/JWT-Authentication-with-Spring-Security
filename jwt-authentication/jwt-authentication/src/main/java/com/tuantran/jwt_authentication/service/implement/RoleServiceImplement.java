package com.tuantran.jwt_authentication.service.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuantran.jwt_authentication.entity.Role;
import com.tuantran.jwt_authentication.repository.RoleRepository;
import com.tuantran.jwt_authentication.service.RoleService;

@Service
public class RoleServiceImplement implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findById(int id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.orElse(null);
    }

}
