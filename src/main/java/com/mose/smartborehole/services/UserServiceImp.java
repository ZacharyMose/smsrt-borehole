package com.mose.smartborehole.services;

import com.mose.smartborehole.dto.UserDTO;
import com.mose.smartborehole.entities.Users;
import com.mose.smartborehole.repositories.UserRepository;
import com.mose.smartborehole.request.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(UserDTO dto) {
        Users user = Users.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword())) // Or generate/send password
                .role(dto.getRole())
                .build();
        return mapToDto(userRepository.save(user));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<Users> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public void changePassword(String email, ChangePasswordRequest request) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        userRepository.findById(id).ifPresentOrElse(userRepository::delete,
                () -> new UsernameNotFoundException("User not found"));
    }
    private UserDTO mapToDto(Users user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
