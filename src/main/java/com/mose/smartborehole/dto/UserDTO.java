package com.mose.smartborehole.dto;

import com.mose.smartborehole.entities.Role;
import com.mose.smartborehole.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDTO {
    private String username;
    private UUID id;
    private String email;
    private Role role;
    private String password;

    public UserDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getName() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
