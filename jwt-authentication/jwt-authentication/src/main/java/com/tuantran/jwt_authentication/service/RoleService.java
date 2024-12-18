package com.tuantran.jwt_authentication.service;

import com.tuantran.jwt_authentication.entity.Role;


public interface RoleService {
    Role findById(int id);
}
