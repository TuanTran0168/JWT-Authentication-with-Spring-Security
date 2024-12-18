package com.tuantran.jwt_authentication.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
    private String name;
    private String username;
    private String password;
    private String role_id;
}
