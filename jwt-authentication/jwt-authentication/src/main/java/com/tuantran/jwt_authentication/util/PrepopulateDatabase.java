package com.tuantran.jwt_authentication.util;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tuantran.jwt_authentication.model.Role;
import com.tuantran.jwt_authentication.repository.RoleRepository;

import java.util.List;

@Component
@AllArgsConstructor
public class PrepopulateDatabase implements CommandLineRunner {
    private final RoleRepository roleRepository;
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Role admin = new Role();
        admin.setName("ADMIN");


        Role user = new Role();
        user.setName("USER");
        roleRepository.saveAll(List.of(admin,user));
    }
}
