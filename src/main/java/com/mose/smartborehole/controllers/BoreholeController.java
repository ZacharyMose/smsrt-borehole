package com.mose.smartborehole.controllers;

import com.mose.smartborehole.dto.BoreholeData;
import com.mose.smartborehole.dto.UserDTO;
import com.mose.smartborehole.entities.Boreholes;
import com.mose.smartborehole.entities.Role;
import com.mose.smartborehole.entities.Users;
import com.mose.smartborehole.repositories.BoreholeRepository;
import com.mose.smartborehole.repositories.UserRepository;
import com.mose.smartborehole.response.BoreholeResponse;
import com.mose.smartborehole.response.BoreholeTeamResponse;
import com.mose.smartborehole.services.BoreholeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boreholes")
public class BoreholeController {
    private final BoreholeRepository boreholeRepository;
    private final BoreholeService boreholeService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createBorehole(@RequestBody BoreholeData dto) {
       BoreholeResponse borehole = boreholeService.createBorehole(dto);
        return ResponseEntity.ok(borehole);
    }
    @GetMapping
    public ResponseEntity<List<Boreholes>> getAllBoreholes() {
        return ResponseEntity.ok(boreholeService.getAllBoreholes());
    }

    @GetMapping("/{boreholeId}/team")
    public ResponseEntity<BoreholeTeamResponse> getBoreholeTeam(@PathVariable UUID boreholeId) {
        BoreholeTeamResponse response = boreholeService.getBoreholeTeam(boreholeId);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{boreholeId}/technicians/{technicianId}")
    public ResponseEntity<?> updateTechnician(
            @PathVariable UUID boreholeId,
            @PathVariable UUID technicianId,
            @RequestBody UserDTO updatedData) {

        Optional<Users> optionalTech = userRepository.findById(technicianId);
        if (optionalTech.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Technician not found");
        }

        Users technician = optionalTech.get();

        // Check if technician belongs to the specified borehole and has TECHNICIAN role
        if (!technician.getBorehole().equals(boreholeId) || technician.getRole() != Role.TECHNICIAN) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Technician does not belong to this borehole");
        }

        // Update fields (you can adjust this based on what fields you're allowing to update)
        technician.setUsername(updatedData.getUsername());
        technician.setEmail(updatedData.getEmail());

        userRepository.save(technician);
        return ResponseEntity.ok("Technician updated successfully");
    }


    @DeleteMapping("/{boreholeId}/technicians/{technicianId}")
    public ResponseEntity<?> deleteTechnicianFromBorehole(
            @PathVariable UUID boreholeId,
            @PathVariable UUID technicianId) {

        Optional<Users> optionalTech = userRepository.findById(technicianId);
        if (optionalTech.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Technician not found");
        }

        Users technician = optionalTech.get();

        // Check if this technician belongs to the correct borehole
        if (!technician.getBorehole().equals(boreholeId) || technician.getRole() != Role.TECHNICIAN) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Technician does not belong to this borehole");
        }

        userRepository.delete(technician);
        return ResponseEntity.ok("Technician deleted from borehole");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBorehole(@PathVariable UUID id, @RequestBody BoreholeData boreholeData) {
        Optional<Boreholes> optionalBorehole = boreholeRepository.findById(id);
        if (optionalBorehole.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Boreholes borehole = optionalBorehole.get();

        // Set new name and location
        borehole.setName(boreholeData.getName());
        borehole.setLocation(boreholeData.getLocation());

        // Set admin
        Optional<Users> adminOpt = userRepository.findById(boreholeData.getAdminId());
        if (adminOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Admin user not found");
        }
        borehole.setAdmin(adminOpt.get());

        // Set technicians
        List<Users> technicians = userRepository.findAllById(boreholeData.getTechnicianIds());
        borehole.setTechnicians(technicians);

        Boreholes updated = boreholeRepository.save(borehole);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBorehole(@PathVariable UUID id) {
        if (!boreholeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        boreholeRepository.deleteById(id);
        return ResponseEntity.ok().body("Borehole deleted successfully");
    }

}
