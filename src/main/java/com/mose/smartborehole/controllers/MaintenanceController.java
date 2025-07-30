package com.mose.smartborehole.controllers;

import com.mose.smartborehole.dto.MaintenanceLogDTO;
import com.mose.smartborehole.response.MaintenanceLogsResponse;
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
    public ResponseEntity<MaintenanceLogsResponse> createLog(@RequestBody MaintenanceLogDTO request, Authentication auth) {
        return ResponseEntity.ok(maintenanceLogService.create(request, auth));
    }

    @GetMapping
    public ResponseEntity<List<MaintenanceLogsResponse>> getAllLogs() {
        return ResponseEntity.ok(maintenanceLogService.getAll());
    }

    @GetMapping("/borehole/{id}")
    public ResponseEntity<List<MaintenanceLogsResponse>> getByBorehole(@PathVariable UUID id) {
        return ResponseEntity.ok(maintenanceLogService.getByBorehole(id));
    }
}
