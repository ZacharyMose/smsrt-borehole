package com.mose.smartborehole.controllers;

import com.mose.smartborehole.entities.Alerts;
import com.mose.smartborehole.entities.Users;
import com.mose.smartborehole.services.AlertService;
import com.mose.smartborehole.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {
    private final AlertService alertsService;

    private final UserService userService;

    // 🔔 Get all alerts for current user
    @GetMapping
    public ResponseEntity<List<Alerts>> getAlerts(@RequestParam UUID userId) {
        Users user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(alertsService.getUserAlerts(user));
    }

    // ✅ Mark alert as read
    @PutMapping("/{id}/read")
    public ResponseEntity<?> markAlertAsRead(@PathVariable Long id) {
        alertsService.markAsRead(id);
        return ResponseEntity.ok().build();
    }
}
