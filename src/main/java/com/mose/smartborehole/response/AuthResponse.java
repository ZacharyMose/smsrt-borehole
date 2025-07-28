package com.mose.smartborehole.response;

import com.mose.smartborehole.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
   private  String token;
    private String username;
    private String role;
}
