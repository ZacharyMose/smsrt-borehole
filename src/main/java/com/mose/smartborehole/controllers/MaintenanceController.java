package com.mose.smartborehole.controllers;

import com.mose.smartborehole.dto.MaintenanceLogDTO;
import com.mose.smartborehole.entities.MaintenanceLogs;
import com.mose.smartborehole.services.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/maintenance")
public class MaintenanceController {

    private final MaintenanceService maintenanceLogService;

    @PostMapping
    public ResponseEntity<Void> logMaintenance(@RequestBody MaintenanceLogDTO dto, Authentication auth) {
        maintenanceLogService.createLog(dto, auth);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{boreholeId}")
    public ResponseEntity<List<MaintenanceLogs>> getLogs(@PathVariable UUID boreholeId) {
        return ResponseEntity.ok(maintenanceLogService.getLogsForBorehole(boreholeId));
    }
}
