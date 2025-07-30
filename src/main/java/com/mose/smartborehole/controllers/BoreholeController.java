package com.mose.smartborehole.controllers;

import com.mose.smartborehole.dto.BoreholeData;
import com.mose.smartborehole.response.BoreholeResponse;
import com.mose.smartborehole.services.BoreholeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boreholes")
public class BoreholeController {
    private final BoreholeService boreholeService;
    @PostMapping
    public ResponseEntity<BoreholeResponse> createBorehole(@RequestBody BoreholeData data) {
        return ResponseEntity.ok(boreholeService.create(data));
    }

    @GetMapping
    public ResponseEntity<List<BoreholeResponse>> getAllBoreholes() {
        return ResponseEntity.ok(boreholeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoreholeResponse> getBoreholeById(@PathVariable UUID id) {
        return ResponseEntity.ok(boreholeService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoreholeResponse> updateBorehole(@PathVariable UUID id, @RequestBody BoreholeData data) {
        return ResponseEntity.ok(boreholeService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorehole(@PathVariable UUID id) {
        boreholeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
