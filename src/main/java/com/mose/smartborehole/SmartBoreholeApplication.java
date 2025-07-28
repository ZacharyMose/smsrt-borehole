package com.mose.smartborehole;

import com.mose.smartborehole.entities.Role;
import com.mose.smartborehole.entities.Users;
import com.mose.smartborehole.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
@EnableMethodSecurity
@SpringBootApplication
@RequiredArgsConstructor
public class SmartBoreholeApplication {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(SmartBoreholeApplication.class, args);
    }

    @Bean
    CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            String adminEmail = "mosezachary198@gmail.com";

            if (userRepository.findByEmail(adminEmail).isEmpty()) {
                Users admin = new Users();
                admin.setUsername("Mose Zachary");
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode("Mose@198")); // You can change this
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
                System.out.println("✅ Admin user created.");
            } else {
                System.out.println("ℹ️ Admin already exists.");
            }
        };
    }
}
