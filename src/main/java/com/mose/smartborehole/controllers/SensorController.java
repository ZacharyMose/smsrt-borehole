package com.mose.smartborehole.controllers;

import com.mose.smartborehole.dto.SensorData;
import com.mose.smartborehole.entities.Sensors;
import com.mose.smartborehole.services.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sensors")
public class SensorController {
    private final SensorService sensorService;

    @PostMapping
    public ResponseEntity<String> receiveSensorData(@RequestBody SensorData dto) {
        sensorService.receiveSensorData(dto);
        return ResponseEntity.ok("Sensor data received");
    }

    @GetMapping("/latest/{boreholeId}")
    public ResponseEntity<List<Sensors>> getLatest(@PathVariable UUID boreholeId) {
        return ResponseEntity.ok(sensorService.getLatestForBorehole(boreholeId));
    }
}
