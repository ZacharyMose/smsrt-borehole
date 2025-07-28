package com.mose.smartborehole.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
