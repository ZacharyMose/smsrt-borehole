package com.mose.smartborehole.request;

import com.mose.smartborehole.entities.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private Role role;
}
