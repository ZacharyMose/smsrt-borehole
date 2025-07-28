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

    public static UserDTO fromEntity(Users userEntity) {
        return UserDTO.builder()
                .id(UUID.randomUUID())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .role(userEntity.getRole())
                .build();
    }

    public static Users toEntity(UserDTO userDTO) {
        return Users.builder()
                .id(UUID.randomUUID())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .role(userDTO.getRole())
                .build();
    }
}
