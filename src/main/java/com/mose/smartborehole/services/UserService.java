package com.mose.smartborehole.services;

import com.mose.smartborehole.dto.UserDTO;
import com.mose.smartborehole.entities.Users;

import java.util.List;
import java.util.Optional;


public interface UserService {
    UserDTO createUser(UserDTO dto); // Admin-only
    List<UserDTO> getAllUsers();
    Optional<Users> findByEmail(String email);
}
