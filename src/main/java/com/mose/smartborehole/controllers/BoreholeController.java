package com.mose.smartborehole.controllers;

import com.mose.smartborehole.dto.BoreholeData;
import com.mose.smartborehole.entities.Boreholes;
import com.mose.smartborehole.repositories.BoreholeRepository;
import com.mose.smartborehole.response.BoreholeResponse;
import com.mose.smartborehole.services.BoreholeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boreholes")
public class BoreholeController {
    private final BoreholeRepository boreholeRepository;
    private final BoreholeService boreholeService;

    @PostMapping
    public ResponseEntity<?> createBorehole(@RequestBody BoreholeData dto) {
       BoreholeResponse borehole = boreholeService.createBorehole(dto);
        return ResponseEntity.ok(borehole);
    }
    @GetMapping
    public ResponseEntity<List<Boreholes>> getAllBoreholes() {
        return ResponseEntity.ok(boreholeService.getAllBoreholes());
    }
}
