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

@Service
@RequiredArgsConstructor
public class BoreholeService {
    private final BoreholeRepository boreholeRepository;
    private final UserRepository userRepository;

    public BoreholeResponse createBorehole(BoreholeData request) {
        Users admin = userRepository.findById(request.getAdminId())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        List<Users> technicians = userRepository.findAllById(request.getTechnicianIds());

        Boreholes borehole = new Boreholes();
        borehole.setName(request.getName());
        borehole.setLocation(request.getLocation());
        borehole.setAdmin(admin);
        borehole.setTechnicians(technicians);

        Boreholes saved = boreholeRepository.save(borehole);

        return mapToResponse(saved);
    }

    private BoreholeResponse mapToResponse(Boreholes borehole) {
        BoreholeResponse response = new BoreholeResponse();
        response.setId(borehole.getId());
        response.setName(borehole.getName());
        response.setLocation(borehole.getLocation());

        // Set Admin
        UserDTO adminDTO = new UserDTO();
        adminDTO.setId(borehole.getAdmin().getId());
        adminDTO.setUsername(borehole.getAdmin().getUsername());
        adminDTO.setEmail(borehole.getAdmin().getEmail());
        response.setAdmin(adminDTO);

        // Set Technicians
        List<UserDTO> techDTOs = new ArrayList<>();
        for (Users tech : borehole.getTechnicians()) {
            UserDTO dto = new UserDTO();
            dto.setId(tech.getId());
            dto.setUsername(tech.getUsername());
            dto.setEmail(tech.getEmail());
            techDTOs.add(dto);
        }
        response.setTechnicians(techDTOs);

        return response;
    }


    public List<Boreholes> getAllBoreholes() {
        return boreholeRepository.findAll();
    }

    public List<String> getEmailsForBoreholeTeam(UUID boreholeId) {
        Boreholes borehole = boreholeRepository.findById(boreholeId)
                .orElseThrow(() -> new RuntimeException("Borehole not found"));

        List<String> emails = new ArrayList<>();

        // Add admin email
        if (borehole.getAdmin() != null) {
            emails.add(borehole.getAdmin().getEmail());
        }

        // Add technician emails
        for (Users tech : borehole.getTechnicians()) {
            emails.add(tech.getEmail());
        }

        return emails;
    }
    public BoreholeTeamResponse getBoreholeTeam(UUID boreholeId) {
        Boreholes borehole = boreholeRepository.findById(boreholeId)
                .orElseThrow(() -> new RuntimeException("Borehole not found"));

        List<TeamMemberDTO> team = new ArrayList<>();

        // Add admin
        Users admin = borehole.getAdmin();
        if (admin != null) {
            team.add(new TeamMemberDTO(admin.getUsername(), admin.getEmail()));
        }

        // Add technicians
        for (Users tech : borehole.getTechnicians()) {
            team.add(new TeamMemberDTO(tech.getUsername(), tech.getEmail()));
        }

        return new BoreholeTeamResponse(team);
    }


}
