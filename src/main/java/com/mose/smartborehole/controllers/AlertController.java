package com.mose.smartborehole.controllers;

import com.mose.smartborehole.dto.AlertDTO;
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

    private final AlertService alertService;

    // Trigger alert generation
    @PostMapping("/generate/{boreholeId}")
    public ResponseEntity<List<AlertDTO>> generateAlerts(@PathVariable UUID boreholeId) {
        List<AlertDTO> alerts = alertService.generateAlertsForBorehole(boreholeId);
        return ResponseEntity.ok(alerts);
    }

    // Get all alerts for a borehole
    @GetMapping("/{boreholeId}")
    public ResponseEntity<List<AlertDTO>> getAlerts(@PathVariable UUID boreholeId) {
        List<AlertDTO> alerts = alertService.getAlertsForBorehole(boreholeId);
        return ResponseEntity.ok(alerts);
    }
}
