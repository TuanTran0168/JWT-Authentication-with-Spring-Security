package com.tuantran.jwt_authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuantran.jwt_authentication.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
