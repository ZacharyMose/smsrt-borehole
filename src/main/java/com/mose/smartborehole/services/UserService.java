package com.mose.smartborehole.services;

import com.mose.smartborehole.dto.UserDTO;
import com.mose.smartborehole.entities.Users;
import com.mose.smartborehole.request.ChangePasswordRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserService {
    UserDTO createUser(UserDTO dto); // Admin-only
    List<UserDTO> getAllUsers();
    Optional<Users> findByEmail(String email);
    Optional<Users> getUserById(UUID id);
    void changePassword(String email ,ChangePasswordRequest dto);
    void deleteUser(UUID id);

}
