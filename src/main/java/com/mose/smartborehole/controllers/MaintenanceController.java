package com.mose.smartborehole.controllers;

import com.mose.smartborehole.dto.MaintenanceLogDTO;
import com.mose.smartborehole.dto.MaintenanceLogRequest;
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

    private final MaintenanceService logsService;

    @PostMapping
    public ResponseEntity<MaintenanceLogs> createLog(@RequestBody MaintenanceLogRequest dto) {
        var log = logsService.createLog(dto);
        return ResponseEntity.ok(log);
    }

    @GetMapping
    public ResponseEntity<List<MaintenanceLogs>> getAllLogs() {
        return ResponseEntity.ok(logsService.getAllLogs());
    }

    @GetMapping("/borehole/{id}")
    public ResponseEntity<List<MaintenanceLogs>> getLogsByBorehole(@PathVariable UUID id) {
        return ResponseEntity.ok(logsService.getLogsByBorehole(id));
    }
}
