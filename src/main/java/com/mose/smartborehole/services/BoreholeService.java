package com.mose.smartborehole.services;

import com.mose.smartborehole.dto.BoreholeData;
import com.mose.smartborehole.dto.TeamMemberDTO;
import com.mose.smartborehole.dto.TechnicianDTO;
import com.mose.smartborehole.dto.UserDTO;
import com.mose.smartborehole.entities.Boreholes;
import com.mose.smartborehole.entities.Users;
import com.mose.smartborehole.repositories.BoreholeRepository;
import com.mose.smartborehole.repositories.UserRepository;
import com.mose.smartborehole.response.BoreholeResponse;
import com.mose.smartborehole.response.BoreholeTeamResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoreholeService {
    private final BoreholeRepository boreholeRepository;
    private final UserRepository userRepository;

    // Create
    public BoreholeResponse create(BoreholeData data) {
        Users admin = userRepository.findById(data.getAdminId())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        List<Users> technicians = userRepository.findAllById(data.getTechnicianIds());
        System.out.println("Technicians found: " + technicians.size());
        technicians.forEach(t -> System.out.println(t.getId() + " - " + t.getUsername()));

        Boreholes borehole = Boreholes.builder()
                .name(data.getName())
                .location(data.getLocation())
                .admin(admin)
                .technicians(technicians)
                .build();

        Boreholes saved = boreholeRepository.save(borehole);
        System.out.println("Saved technicians: " + saved.getTechnicians().size());
        return mapToDTO(saved);
    }

    // Read all
    public List<BoreholeResponse> getAll() {
        List<Boreholes> boreholes = boreholeRepository.findAll();

        return boreholes.stream().map(borehole -> {
            List<TechnicianDTO> techs = borehole.getTechnicians().stream()
                    .map(user -> new TechnicianDTO(user.getUsername(), user.getEmail()))
                    .collect(Collectors.toList());

            return BoreholeResponse.builder()
                    .id(borehole.getId())
                    .name(borehole.getName())
                    .location(borehole.getLocation())
                    .technicians(techs)
                    .build();
        }).collect(Collectors.toList());
    }

    // Read by ID
    public BoreholeResponse getById(UUID id) {
        Boreholes borehole = boreholeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borehole not found"));
        return mapToDTO(borehole);
    }

    // Update
    public BoreholeResponse update(UUID id, BoreholeData data) {
        Boreholes borehole = boreholeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borehole not found"));

        Users admin = userRepository.findById(data.getAdminId())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        List<Users> technicians = userRepository.findAllById(data.getTechnicianIds());

        borehole.setName(data.getName());
        borehole.setLocation(data.getLocation());
        borehole.setAdmin(admin);
        borehole.setTechnicians(technicians);

        return mapToDTO(boreholeRepository.save(borehole));
    }

    // Delete
    public void delete(UUID id) {
        boreholeRepository.deleteById(id);
    }

    // Helper: map to DTO
    private BoreholeResponse mapToDTO(Boreholes borehole) {
        List<TechnicianDTO> techs = borehole.getTechnicians().stream()
                .map(tech -> new TechnicianDTO( tech.getUsername(), tech.getEmail()))
                .collect(Collectors.toList());

        return new BoreholeResponse(
                borehole.getId(),
                borehole.getName(),
                borehole.getLocation(),
                techs
        );
    }
}
