package com.mose.smartborehole.controllers;

import com.mose.smartborehole.dto.SensorData;
import com.mose.smartborehole.entities.Sensors;
import com.mose.smartborehole.services.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sensors")
public class SensorController {
    private final SensorService sensorService;

    @PostMapping
    public ResponseEntity<?> saveSensorData(@RequestBody SensorData dto) {
        sensorService.save(dto);
        return ResponseEntity.ok("Sensor data saved.");
    }

    @GetMapping
    public ResponseEntity<List<Sensors>> getAll() {
        return ResponseEntity.ok(sensorService.getAll());
    }
}
